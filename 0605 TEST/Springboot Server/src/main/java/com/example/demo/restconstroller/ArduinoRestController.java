package com.example.demo.restconstroller;


import com.fazecast.jSerialComm.SerialPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.*;

@RestController
@Slf4j
@RequestMapping("/arduino")
public class ArduinoRestController {
    //----------------------------------------------------------------
    // + import 란에서 com.fazecast.jSerialComm.SerialPort; 가 아두이노와 연결되게 해주는 라이브러리. 중요함.
    // 필드값 정의, ex) 시리얼 포트 변수, 로그 메세지 문자열 변수 정의.
    //----------------------------------------------------------------
    private SerialPort serialPort;
    private OutputStream outputStream;
    private InputStream inputStream;

    private String LedLog;
    private String TmpLog;
    private String LightLog;
    private String DistanceLog;

    // 연결 설정 메서드: /connection/{COM} 경로로 들어오는 GET 요청을 처리.
    @GetMapping("/connection/{COM}")
    public ResponseEntity<String> setConnection(@PathVariable("COM") String COM, HttpServletRequest request) {
        log.info("GET /arduino/connection " + COM + " IP: " + request.getRemoteAddr());

        if (serialPort != null) {
            serialPort.closePort();
            serialPort = null;
        }

        //Port Setting
        serialPort = SerialPort.getCommPort(COM); // 특정 직렬 포트(COM)와 연결

        //Connection Setting 연결 세팅, 시리얼 포트 기능을 호출해서 세팅하는것.
        serialPort.setBaudRate(9600);
        serialPort.setNumDataBits(8);
        serialPort.setNumStopBits(0);
        serialPort.setParity(0);
        serialPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_BLOCKING,2000,0);

        //----------------------------------------------------------------
        // 시리얼 포트와의 연결 유무를 확인하고, 연결이 열렷을떄와 연결이 실패했을 때를 확인하는 과정.
        //----------------------------------------------------------------
        boolean isOpen = serialPort.openPort();
        log.info("isOpen ? " + isOpen);

        if (isOpen) {
            this.outputStream = serialPort.getOutputStream();
            this.inputStream = serialPort.getInputStream();

            //----------------------------------------------------------------
            //수신 스레드 동작
            //----------------------------------------------------------------
            Worker worker = new Worker();
            Thread th = new Thread(worker);
            th.start();

            return new ResponseEntity("Connection Success!", HttpStatus.OK);
        } else {
            return new ResponseEntity("Connection Fail...!", HttpStatus.BAD_GATEWAY);
        }


    }
    //----------------------------------------------------------------
    // LED ON OFF 를 담당하는 코드 value 값에 따라 LED 상태가 결정됨.
    //----------------------------------------------------------------
    @GetMapping("/led/{value}")
    public void led_Control(@PathVariable String value, HttpServletRequest request) throws IOException {
        log.info("GET /arduino/led/value : " + value + " IP : " + request.getRemoteAddr());
        if (serialPort.isOpen()) {
            outputStream.write(value.getBytes());
            outputStream.flush();
        }
    }

    //----------------------------------------------------------------
    // LED 온도 조도 초음파 거리에 관한 정보를 제공하는 코드
    //----------------------------------------------------------------
    @GetMapping("/message/led")
    public String led_Message() throws InterruptedException {
        return LedLog;
    }

    @GetMapping("/message/tmp")
    public String tmp_Message() {
        return TmpLog;
    }

    @GetMapping("/message/light")
    public String light_Message() {
        return LightLog;
    }

    @GetMapping("/message/distance")
    public String distance_Message() {
        return DistanceLog;
    }


    //----------------------------------------------------------------
    // 수신 스레드 클래스 해당 코드가, 아두이노에서 온 정보를 처리하는 코드인 것은 알겠으나 자세한 내용에 대해서 살짝 머리가 아픔.
    // 해당 코드가 배열 타입으로 자료를 받아와서 나열하는거고 예외처리해서 오류 떴을때를 대비하는 것도 알겟고 배열 0 에 LEDLOG 놓다가 LEDLOG가 안왓을때를 대비해서
    // 안왓을떈 배열 0 번에 온도 로그를 놓는거 같습니다.
    //----------------------------------------------------------------
    class Worker implements Runnable {
        DataInputStream din;
        @Override
        public void run() {
            din = new DataInputStream(inputStream);
            try{
                while(!Thread.interrupted()) {
                    if (din != null) {
                        String data = din.readLine();
                        System.out.println("[DATA] : " + data);
                        String[] arr = data.split("_"); //LED ,TMP , LIGHT , DIS // 3,4
                        try {
                            if (arr.length > 3) {
                                LedLog = arr[0];
                                TmpLog = arr[1];
                                LightLog = arr[2];
                                DistanceLog = arr[3];
                            } else {
                                TmpLog = arr[0];
                                LightLog = arr[1];
                                DistanceLog = arr[2];
                            }
                        }catch(ArrayIndexOutOfBoundsException e1)
                        {e1.printStackTrace();}
//                        if(data.startsWith("LED:")){
//                            LedLog = data;
//                        }else if(data.startsWith("TMP:")) {
//                            TmpLog = data;
//                        }else if(data.startsWith("LIGHT:")){
//                            LightLog = data;
//                        }else if(data.startsWith("DIS:")){
//                            DistanceLog = data;
//                        }

                    }
                    Thread.sleep(200);
                }
            }catch(Exception e){
                e.printStackTrace();
            }


        }
    }
    //----------------------------------------------------------------

}











