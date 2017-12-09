package controller;

/**
 * 모델과의 작업 완료후 이동하게 될 페이지 정보와
 * 포워드, 리다이렉트 중 어떤 이동방식을 사용할지에 
 * 대한 정보를 담고 있다.
 */
public class ActionForward {
	private boolean isRedirect = false;
	private String path = null;
	
	public boolean isRedirect() {
		return isRedirect;
	}

	public String getPath() {
		return path;
	}

	public void setRedirect(boolean b) {
		isRedirect = b;
	}

	public void setPath(String string) {
		path = string;
	}
}
