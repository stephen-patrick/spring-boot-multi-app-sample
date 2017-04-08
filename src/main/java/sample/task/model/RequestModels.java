package sample.task.model;

public class RequestModels {

	public static abstract class BinaryOperationRequest {

		private int x;
		private int y;

		public BinaryOperationRequest(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public int getX() {
			return x;
		}

		public int getY() {
			return y;
		}

		public void setX(int x) {
			this.x = x;
		}

		public void setY(int y) {
			this.y = y;
		}
	}

	public static class MultiplyTaskRequest extends BinaryOperationRequest {

		public MultiplyTaskRequest() {
			super(0, 0);
		}

		public MultiplyTaskRequest(int x, int y) {
			super(x, y);
		}
	}
}
