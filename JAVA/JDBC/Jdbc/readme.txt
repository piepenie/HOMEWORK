JDBC 연결을 영상을 따라해보면서 수업 강의 자료와 비교 수정 후 문제 해결완료 

해당 오류는 라이브러리 설치 오류로 인한 문제였음을 확인 설치 후 정상 가동함 - 0412

		public Connection getConn() {
			return getConn(); 
		}
코드 확인중 이부분이 무한 루프가 돌고있는 것을 확인 처리방법으로 
public class Testdb {
	
	private static Connection conn; // 전역변수로 컨을 보내고 
	
	public static void main(String[] args){
  ....

    public static Connection getConn() {
        return conn;
    } 로 처리함 
}
해당 내용에 관해서 

private static Connection conn;라인은 conn을 전역 변수로 선언하고 있습니다.

전역 변수로 선언한 이유:

conn 변수가 main() 메서드 내에서 선언되지 않고 클래스 레벨에서 선언되어 있기 때문에 전역 변수로 볼 수 있습니다.
데이터베이스 연결 객체인 conn은 여러 메서드에서 사용될 가능성이 있기 때문에 클래스 전역에서 사용할 수 있도록 전역 변수로 선언했습니다.
static 키워드 사용 이유:

static 키워드는 해당 변수가 클래스 수준의 변수임을 나타냅니다. 즉, 클래스의 모든 인스턴스에서 공유됩니다.
main() 메서드는 정적(static) 메서드이므로, 정적 변수인 conn에 접근할 수 있어야 합니다. 따라서 conn 변수를 정적(static)으로 선언하여 main() 메서드에서 접근할 수 있도록 했습니다.
정적 변수를 사용하면 객체 인스턴스를 생성하지 않고도 클래스 이름을 통해 변수에 접근할 수 있습니다.
이렇게 전역 변수로 선언된 conn을 사용하면 여러 메서드에서 데이터베이스 연결 객체에 접근할 수 있으며, 객체 인스턴스를 생성하지 않고도 클래스에서 직접 접근할 수 있습니다.

라고 GPT 답을 받앗는데 이 부분은 13일에 해결해보도록 하겠다.
