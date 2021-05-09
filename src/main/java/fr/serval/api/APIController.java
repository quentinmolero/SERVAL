package fr.serval.api;

public class APIController {
    private static APIController instance;

    private APIAuthController APIAuthController;
    private APIProjectController APIProjectController;

    public APIController() {
        APIAuthController = new APIAuthController();
        APIProjectController = new APIProjectController();
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

    public APIAuthController getGitAuthController() {
        return APIAuthController;
    }

    public APIProjectController getGitRepoController() {
        return APIProjectController;
    }
}
