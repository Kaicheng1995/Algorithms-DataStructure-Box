package gitlet;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;

public class Gitlet implements Serializable {

    private Stage S;
    private String Head;
    private String Branch;
    private ArrayList<String> Commits;
    private HashMap<String, String> Branches;

    // quick print
    public static void Message (String s) {
        System.out.println(s);
    }

    /** make a initial gitlet.*/
    public Gitlet() {
        Commits = new ArrayList<String>();
        Branches = new HashMap<String, String>();
        S = new Stage();
        Branch = "master";

        Commit init = new Commit("initial commit", new Date(0),
                null, new HashMap<String, String>());
        String sha = Utils.sha1(Utils.serialize(init));
        saveCommit(init, sha);

        Head = sha;
        S.setHead(init);
        Commits.add(sha);
        Branches.put("master", sha);
    }

    /** save a commit.*/
    public void saveCommit(Commit c, String sha) {
        File f = new File(".gitlet/commits" + sha);
        Utils.writeObject(f, c);
    }

    /** add a file.*/
    public void add(String fileName) {
        S.add(fileName);
    }

    /** make a commit.*/
    public void commit(String info) {
        Commit commit = S.commit(info);
        if (commit == null) {
            return;
        }
        String sha = Utils.sha1(Utils.serialize(commit));
        Commits.add(sha);
        saveCommit(commit, sha);
        Head = sha;
        Branches.put(Branch, Head);
    }

    /** make a commit.*/
    public void mergeCommit(String i, String p1, String p2) {
        Commit c = S.mergeCommit(i, p1, p2);
        if (c == null) {
            return;
        }
        String sha = Utils.sha1(Utils.serialize(c));
        Commits.add(sha);
        saveCommit(c, sha);
        Head = sha;
        Branches.put(Branch, Head);
    }

    /** remove a file.*/
    public void rm(String fileName) {
        S.rm(fileName);
    }

    /** print the log of head.*/
    public void log() {
        String sha = Head;
        while (sha != null) {
            Commit c = getCommit(sha);
            Message("===");
            Message("commit " + sha);
            if (c.get_MP1() != null) {
                Message("Merge: " + c.get_MP1()
                        + " " + c.get_MP2());
            }
            Date time = c.getTime();
            Message(String.format("Date: %1$ta %1$tb"
                    + " %1$te %1$tT %1$tY %1$tz", time));
            Message(c.get_I());
            Message("");
            sha = c.get_P();
        }
    }

    /** print the global log.*/
    public void globalLog() {
        for (String sha : Commits) {
            File f = new File(".gitlet/commits" + sha);
            Commit c = Utils.readObject(f, Commit.class);
            Message("===");
            Message("commit " + sha);
            if (c.get_MP1() != null) {
                Message("Merge: " + c.get_MP1()
                        + " " + c.get_MP2());
            }
            Date t = c.getTime();
            Message(String.format("Date: %1$ta"
                    + " %1$tb %1$te %1$tT %1$tY %1$tz", t));
            Message(c.get_I());
            Message("");
        }
    }

    /** find commits with given message.*/
    public void find(String i) {
        boolean found = false;
        for (String sha : Commits) {
            File f = new File(".gitlet/commits" + sha);
            Commit c = Utils.readObject(f, Commit.class);
            if (c.get_I().equals(i)) {
                Message(sha);
                found = true;
            }
        }
        if (!found) {
            Message("Found no commit with that message.");
        }
    }

    /** print branch status.*/
    public void Status_B() {
        Message("=== Branches ===");
        PriorityQueue<String> branches = new PriorityQueue<>();
        for (String key : Branches.keySet()) {
            branches.add(key);
        }
        while (!branches.isEmpty()) {
            String branch = branches.remove();
            if (branch.equals(this.Branch)) {
                Message("*" + branch);
            } else {
                Message(branch);
            }
        }
        Message("");
    }

    /** print staged status.*/
    public void Status_S() {
        Message("=== Staged Files ===");
        PriorityQueue<String> stagedFiles = new PriorityQueue<>();
        for (String key : S.SGet().keySet()) {
            stagedFiles.add(key);
        }
        while (!stagedFiles.isEmpty()) {
            String fileName = stagedFiles.remove();
            Message(fileName);
        }
        Message("");
    }

    /** print removed status.*/
    public void Status_R() {
        Message("=== Removed Files ===");
        Collections.sort(S.MGet());
        for (String fileName : S.MGet()) {
            Message(fileName);
        }
        Message("");
    }

    /** print modified status.*/
    public void Status_M() {
        Message("=== Modifications Not Staged For Commit ===");
        ArrayList<String> modified = new ArrayList<>();
        List<String> workingDirectoryFiles = Utils.plainFilenamesIn("./");
        Commit commit = getHead();
        HashMap<String, String> blobs = commit.getBlobs();
        for (String key: blobs.keySet()) {
            if (!workingDirectoryFiles.contains(key)
                    && !S.MGet().contains(key)) {
                modified.add(key + " (deleted)");
            } else if (workingDirectoryFiles.contains(key)
                    && !S.SGet().containsKey(key)
                    && isModified(key, blobs.get(key))) {
                modified.add(key + " (modified)");
            }
        }
        Collections.sort(modified);
        for (String fileName : modified) {
            Message(fileName);
        }
        Message("");
    }

    /** get the commit with given sha1.*/
    public static Commit getCommit(String sha) {
        File f = new File(".gitlet/commits" + sha);
        return Utils.readObject(f, Commit.class);
    }

    /** get the head commit.*/
    public Commit getHead() {
        return getCommit(Head);
    }

    /** get contents of the given file.*/
    public byte[] getContents(String fileName) {
        File f = new File(fileName);
        return Utils.readContents(f);
    }

    /** check if the file is modified.*/
    public boolean isModified(String fileName, String sha) {
        String sha1 = Utils.sha1(getContents(fileName));
        return !sha1.equals(sha);
    }

    /** print untracked status.*/
    public void Status_UT() {
        Message("=== Untracked Files ===");
        ArrayList<String> untracked = new ArrayList<>();
        List<String> workingDirectoryFiles = Utils.plainFilenamesIn("./");
        Commit c = getHead();
        for (String fileName: workingDirectoryFiles) {
            if (!S.SGet().containsKey(fileName)
                    && !c.getBlobs().containsKey(fileName)
                    && !S.MGet().contains(fileName)) {
                untracked.add(fileName);
            }
        }
        Collections.sort(untracked);
        for (String fileName : untracked) {
            Message(fileName);
        }
        Message("");
    }

    /** print status.*/
    public void status() {
        Status_B();
        Status_S();
        Status_R();
        Status_M();
        Status_UT();
    }

    /** fast checkout.*/
    public void checkout(String fileName) {
        File f = new File(".gitlet/commits" + Head);
        Commit c = Utils.readObject(f, Commit.class);
        String sha = c.SHA_F(fileName);
        if (sha == null) {
            Message("File does not exist in that commit.");
            return;
        }
        File fb = new File(".gitlet/blobs" + sha);
        byte[] blob = Utils.readContents(fb);
        File file = new File(fileName);
        Utils.writeContents(file, blob);
    }

    /** checkout a given commit.*/
    public void checkout(String commitID, String fileName) {
        boolean isFind = false;
        for (String key: Commits) {
            if (key.substring(0, commitID.length()).equals(commitID)) {
                commitID = key;
                isFind = true;
                break;
            }
        }
        if (!isFind) {
            Message("No commit with that id exists.");
            return;
        }
        File f = new File(".gitlet/commits" + commitID);
        Commit c = Utils.readObject(f, Commit.class);
        String sha = c.SHA_F(fileName);
        if (sha == null) {
            Message("File does not exist in that commit.");
            return;
        }
        File fb = new File(".gitlet/blobs" + sha);
        byte[] blob = Utils.readContents(fb);
        File file = new File(fileName);
        Utils.writeContents(file, blob);
    }

    /** checkout a branch.*/
    public void checkoutBranch(String branchName) {
        String branch = Branches.get(branchName);
        if (branch == null) {
            Message("No such branch exists.");
            return;
        }
        if (branchName.equals(Branch)) {
            Message("No need to checkout the current branch.");
            return;
        }
        Commit c = getCommit(branch);
        HashMap<String, String> branchBlobs = c.getBlobs();

        Commit commitHead = getHead();
        HashMap<String, String> headBlobs = commitHead.getBlobs();
        List<String> workingDirectoryFiles = Utils.plainFilenamesIn("./");
        if (isUntracked(c)) {
            return;
        }

        HashMap<String, String> blobs = c.getBlobs();
        for (String key: blobs.keySet()) {
            String blobID = blobs.get(key);
            File fblob = new File(".gitlet/blobs" + blobID);
            byte[] blob = Utils.readContents(fblob);
            File file = new File(key);
            Utils.writeContents(file, blob);
        }

        for (String fileName: workingDirectoryFiles) {
            if (!blobs.keySet().contains(fileName)) {
                Utils.restrictedDelete(fileName);
            }
        }
        S.clear();
        S.setHead(c);
        Head = branch;
        Branch = branchName;
    }

    /** make a new branch.*/
    public void branch(String branchName) {
        if (Branches.get(branchName) != null) {
            Message("branch with that name already exists.");
            return;
        }
        Branches.put(branchName, Head);
    }

    /** remove a branch.*/
    public void rmBranch(String branchName) {
        if (branchName.equals(Branch)) {
            Message("Cannot remove the current branch.");
            return;
        }
        if (Branches.remove(branchName) == null) {
            Message("A branch with that name does not exist.");
            return;
        }
    }

    /** reset to a commit.*/
    public void reset(String commitID) {
        if (!Commits.contains(commitID)) {
            Message("No commit with that id exists.");
            return;
        }

        Commit c = getCommit(commitID);
        HashMap<String, String> branchBlobs = c.getBlobs();

        Commit commitHead = getHead();
        HashMap<String, String> headBlobs = commitHead.getBlobs();
        List<String> workingDirectoryFiles = Utils.plainFilenamesIn("./");
        if (isUntracked(c)) {
            return;
        }

        HashMap<String, String> blobs = c.getBlobs();
        for (String key: blobs.keySet()) {
            String blobID = blobs.get(key);
            File fblob = new File(".gitlet/blobs" + blobID);
            byte[] blob = Utils.readContents(fblob);
            File file = new File(key);
            Utils.writeContents(file, blob);
        }

        for (String fileName: workingDirectoryFiles) {
            if (branchBlobs.get(fileName) == null) {
                Utils.restrictedDelete(fileName);
            }
        }
        S.clear();
        S.setHead(c);
        Head = commitID;
        Branches.put(Branch, commitID);
    }

    /** check if there is any file is untracked.*/
    public boolean isUntracked(Commit given) {
        Commit current = getHead();
        HashMap headBlobs = current.getBlobs();
        HashMap givenBlobs = given.getBlobs();
        List<String> workingDirectoryFiles = Utils.plainFilenamesIn("./");
        for (String fileName: workingDirectoryFiles) {
            if (!headBlobs.containsKey(fileName)
                    && !S.SGet().containsKey(fileName)) {
                Message("There is an untracked file in the way;"
                        + " delete it, or add and commit it first.");
                return true;
            }
        }
        return false;
    }

    /** collection of error message.*/
    public boolean isErrorMessage(String branchName) {
        if (!S.isClear()) {
            Message("You have uncommitted changes.");
            return true;
        }
        if (!Branches.containsKey(branchName)) {
            Message("A branch with that name does not exist.");
            return true;
        }
        if (branchName.equals(Branch)) {
            Message("Cannot merge a branch with itself.");
            return true;
        }
        Commit current = getHead();
        Commit given = getCommit(Branches.get(branchName));
        Commit split = splitPoint(current, given);

        if (given.equals(split)) {
            Message("Given branch is an ancestor"
                    + " of the current branch.");
            return true;
        }
        if (current.equals(split)) {
            Head = given.getSHA();
            Branches.put(Branch, Head);
            Message("Current branch fast-forwarded.");
            return true;
        }
        return false;
    }

    /** write the contents of given file into working directory.*/
    public void write(String fileName, String givenFileSHA) {
        File file = new File(fileName);
        File givenFile = new File(".gitlet/blobs" + givenFileSHA);
        Utils.writeContents(file, Utils.readContents(givenFile));
        add(fileName);
    }

    /** write the contents of conflict into working directory.*/
    public void writeConflict1(String fileName, String givenFileSHA) {
        File workingFile = new File(fileName);
        File givenFile = new File(".gitlet/blobs" + givenFileSHA);
        String givenContents = Utils.readContentsAsString(givenFile);
        Utils.writeContents(workingFile, "<<<<<<< HEAD"
                + System.lineSeparator()
                + "=======" + System.lineSeparator() + givenContents
                + ">>>>>>>" + System.lineSeparator());
        add(fileName);
    }

    /** write the contents of conflict into working directory.*/
    public void writeConflict2(String fileName,
                               String currFileSHA,
                               String givenFileSHA) {
        File workingFile = new File(fileName);
        File currFile = new File(".gitlet/blobs" + currFileSHA);
        File givenFile = new File(".gitlet/blobs" + givenFileSHA);
        String currContents = Utils.readContentsAsString(currFile);
        String givenContents = Utils.readContentsAsString(givenFile);
        Utils.writeContents(workingFile, "<<<<<<< HEAD\n"
                + currContents + "=======\n" + givenContents
                + ">>>>>>>\n");
        add(fileName);
    }

    /** write the contents of conflict into working directory.*/
    public void writeConflict3(String fileName, String currFileSHA) {
        File workingFile = new File(fileName);
        File currFile = new File(".gitlet/blobs" + currFileSHA);
        String currContents = Utils.readContentsAsString(currFile);
        Utils.writeContents(workingFile, "<<<<<<< HEAD"
                + System.lineSeparator()
                + currContents + "=======" + System.lineSeparator()
                + ">>>>>>>\n");
        add(fileName);
    }


    public boolean pred1(String given, String split, String curr) {
        if (given != null && split != null) {
            return !given.equals(split) && split.equals(curr);
        }
        return false;
    }

    public boolean pred2(String given, String split, String curr) {
        if (given != null && split != null) {
            return !given.equals(split) && split.equals(curr);
        }
        return false;
    }

    public boolean pred3(String given, String split, String curr) {
        if (given != null) {
            return !given.equals(split) && given.equals(curr);
        }
        return false;
    }

    public boolean pred4(String given, String split, String curr) {
        if (given != null && split != null && curr != null) {
            return !given.equals(curr) && !curr.equals(split) && !split.equals(given);
        }
        return false;
    }

    public boolean pred5(String given, String split, String curr) {
        if (curr != null) {
            return split != null && given == null && curr.equals(split);
        }
        return false;
    }

    /** merge the current branch with the given branch.*/
    public void merge(String branchName) {
        if (isErrorMessage(branchName)) {
            return;
        }
        Commit given = getCommit(Branches.get(branchName));
        Commit splitPoint = splitPoint(getHead(), given);
        HashMap<String, String> currBlobs = getHead().getBlobs();
        HashMap<String, String> givenBlobs = given.getBlobs();
        HashMap<String, String> splitBlobs = splitPoint.getBlobs();
        if (isUntracked(given)) {
            return;
        }
        boolean isConflict = false;
        for (String fileName: givenBlobs.keySet()) {
            String currSHA = currBlobs.get(fileName);
            String givenSHA = givenBlobs.get(fileName);
            String splitSHA = splitBlobs.get(fileName);
            if (currSHA == null && splitSHA == null) {
                write(fileName, givenSHA);
                continue;
            }
            if (pred1(givenSHA, splitSHA, currSHA)) {
                writeConflict1(fileName, givenSHA);
                isConflict = true;
            }
            if (givenSHA.equals(splitSHA)) {
                continue;
            }
            if (pred2(givenSHA, splitSHA, currSHA)) {
                write(fileName, givenSHA);
                continue;
            }
            if (pred3(givenSHA, splitSHA, currSHA)) {
                continue;
            }
            if (pred4(givenSHA, splitSHA, currSHA)) {
                writeConflict2(fileName, currSHA, givenSHA);
                isConflict = true;
            }
        }
        for (String fileName: currBlobs.keySet()) {
            String currSHA = currBlobs.get(fileName);
            String givenSHA = givenBlobs.get(fileName);
            String splitSHA = splitBlobs.get(fileName);
            if (pred5(givenSHA, splitSHA, currSHA)) {
                rm(fileName);
            }
            if (splitSHA != null && givenSHA == null
                    && !currSHA.equals(splitSHA)) {
                writeConflict3(fileName, currSHA);
                isConflict = true;
            }
        }
        String parent1 = Branches.get(branchName).substring(0, 7);
        mergeCommit("Merged " + branchName + " into " + Branch + ".",
                parent1, Head.substring(0, 7));
        if (isConflict) {
            Message("Encountered a merge conflict.");
        }
    }

    /** get the split point of two commits.*/
    public static Commit splitPoint(Commit current, Commit given) {
        if (given == null) {
            return current;
        }
        int len = current.distance() - given.distance();
        if (len < 0) {
            given = given.shorten(-len);
        } else {
            current = current.shorten(len);
        }
        while (!current.equals(given)) {
            current = getCommit(current.get_P());
            given = getCommit(given.get_P());
        }
        return given;
    }
}