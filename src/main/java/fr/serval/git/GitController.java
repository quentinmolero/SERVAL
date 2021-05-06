package fr.serval.git;

public class GitController {
    private static GitController instance;

    private GitAuthController gitAuthController;
    private GitRepoController gitRepoController;

    public GitController() {
        gitAuthController = new GitAuthController();
        gitRepoController = new GitRepoController();
    }

    public static GitController getInstance() {
        if (isInstanceNotInitialized()) {
            instance = new GitController();
        }
        return instance;
    }

    public static void deleteInstance() {
        if (instance != null) {
            instance = null;
        }
    }

    public static GitController resetInstance() {
        deleteInstance();
        return getInstance();
    }

    private static boolean isInstanceNotInitialized() {
        return instance == null;
    }

    public GitAuthController getGitAuthController() {
        return gitAuthController;
    }

    public GitRepoController getGitRepoController() {
        return gitRepoController;
    }
}
