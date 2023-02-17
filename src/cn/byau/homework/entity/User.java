package cn.byau.homework.entity;

public class User {
	    // 表中所有字段
	    private String userID;
	    private String userName;
	    private String password;
	    private String email;
	    private String userType;
		public User() {
			super();
		}
		public User(String userID, String userName, String password, String email, 
				String userType) {
			super();
			this.userID = userID;
			this.userName = userName;
			this.password = password;
			this.email = email;
			this.userType = userType;
		}
		public String getUserID() {
			return userID;
		}
		public void setUserID(String userID) {
			this.userID = userID;
		}
		public String getUserName() {
			return userName;
		}
		public void setUserName(String userName) {
			this.userName = userName;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getUserType() {
			return userType;
		}
		public void setUserType(String userType) {
			this.userType = userType;
		}
}
