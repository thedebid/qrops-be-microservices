package np.com.debid.qropsorderservice.constant;

public class Constant {
    public static class Messages {
        public static final String ORDER_CREATED = "Order created successfully.";
        public static final String ORDER_NOT_FOUND = "Error: Order is not found.";
        public static final String ORDER_DELETED = "Order deleted successfully.";
        public static final String ORDER_FETCHED = "Order fetched successfully.";
        public static final String ORDERS_FETCHED = "Orders fetched successfully.";
    }

    public static class ErrorCodes {
        public static final int ORDER_NOT_FOUND_ERROR_CODE = 4003;
    }
}