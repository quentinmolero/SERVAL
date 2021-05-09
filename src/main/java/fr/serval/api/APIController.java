package fr.serval.api;

public class APIController {
    private static APIController instance;

    private final APIAuthController APIAuthController;
    private final APIProjectController APIProjectController;
    private final APITaskController APITaskController;

    private APIController() {
        APIAuthController = new APIAuthController();
        APIProjectController = new APIProjectController();
        APITaskController = new APITaskController();
    }

    public static APIController getInstance() {
        if (isInstanceNotInitialized()) {
            instance = new APIController();
        }
        return instance;
    }

    public static void deleteInstance() {
        if (instance != null) {
            instance = null;
        }
    }

    public static APIController resetInstance() {
        deleteInstance();
        return getInstance();
    }

    private static boolean isInstanceNotInitialized() {
        return instance == null;
    }

    public APIAuthController getAPIAuthController() {
        return APIAuthController;
    }

    public APIProjectController getAPIProjectController() {
        return APIProjectController;
    }

    public APITaskController getAPITaskController() {
        return APITaskController;
    }
}
