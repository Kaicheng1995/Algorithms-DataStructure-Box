package gitlet;

import java.io.File;
import java.io.IOException;
import static gitlet.Gitlet.*;
import static gitlet.Utils.*;

public class Main {

    /** main method. */
    public static void main(String... args) throws IOException{
        if (args.length == 0) {
            Message("Please enter a command.");
            return;
        }
        String command = args[0];
        if (command.equals("init")) {
            INIT();
        } else {
            File f = new File(".gitlet/gitlet");
            if (!f.exists()) {
                Message("Not in an initialized"
                        + " Gitlet directory.");
                return;
            }
            Gitlet current = Utils.readObject(f, Gitlet.class);
            switch (command) {
            case "add":
                ADD(current, args); break;
            case "commit":
                COMMIT(current, args); break;
            case "rm":
                REMOVE(current, args); break;
            case "log":
                LOG(current, args); break;
            case "global-log":
                GLOBAL_LOG(current, args); break;
            case "find":
                FIND(current, args); break;
            case "status":
                STATUS(current, args); break;
            case "checkout":
                CHECKOUT(current, args); break;
            case "branch":
                BRANCH(current, args); break;
            case "rm-branch":
                REMOVE_BRANCH(current, args); break;
            case "reset":
                RESET(current, args); break;
            case "merge":
                MERGE(current, args); break;
            default:
                Message("No command with that name exists.");
                break;
            }
            SAVE(current);
        }
    }

    // quick print
    public static void Message (String s) {
        System.out.println(s);
    }

    /** initialize the program and make directory.*/
    public static void INIT() {
        File GitSystem = new File(".gitlet/");
        if (GitSystem.exists()) {
            Message("A Gitlet version-control system already"
                    + " exists in the current directory.");
            return;
        }
        GitSystem.mkdirs();
        File B = new File(".gitlet/blobs");
        B.mkdirs();
        File C = new File(".gitlet/commits");
        C.mkdirs();
        Gitlet current = new Gitlet();
        SAVE(current);
    }

    /** save the situation after running a command.*/
    public static void SAVE(Gitlet current) {
        File tobesaved = new File(".gitlet/gitlet");
        writeObject(tobesaved, current);
    }

    /** add command method.*/
    static void ADD(Gitlet current, String...args) {
        if (args.length != 2) {
            Message("Incorrect operands.");
            return;
        }
        current.add(args[1]);
    }

    /** commit command method.*/
    static void COMMIT(Gitlet current, String...args) {
        if (args.length == 1) {
            Message("Please enter a commit message.");
            return;
        }
        if (args.length > 2) {
            Message("Incorrect operands.");
            return;
        }
        current.commit(args[1]);
    }
    /** rm command method.*/
    static void REMOVE(Gitlet current, String...args) {
        if (args.length != 2) {
            Message("Incorrect operands.");
            return;
        }
        current.rm(args[1]);
    }
    /** log command method.*/
    static void LOG(Gitlet current, String...args) {
        if (args.length != 1) {
            Message("Incorrect operands.");
            return;
        }
        current.log();
    }
    /** global-log command method.*/
    static void GLOBAL_LOG(Gitlet current, String...args) {
        if (args.length != 1) {
            Message("Incorrect operands.");
            return;
        }
        current.globalLog();
    }
    /** find command method.*/
    static void FIND(Gitlet current, String...args) {
        if (args.length != 2) {
            Message("Incorrect operands.");
            return;
        }
        current.find(args[1]);
    }
    /** status command method.*/
    static void STATUS(Gitlet current, String...args) {
        if (args.length != 1) {
            System.out.println("Incorrect operands.");
            return;
        }
        current.status();
    }

    /** branch command method.*/
    static void BRANCH(Gitlet current, String...args) {
        if (args.length != 2) {
            Message("Incorrect operands.");
            return;
        }
        current.branch(args[1]);
    }
    /** checkout command method.*/
    static void CHECKOUT(Gitlet current, String...args) {
        if (args.length == 2) {
            current.checkoutBranch(args[1]);
        } else if (args.length == 3) {
            if (!args[1].equals("--")) {
                Message("Incorrect operands.");
                return;
            }
            current.checkout(args[2]);
        } else if (args.length == 4) {
            if (!args[2].equals("--")) {
                Message("Incorrect operands.");
                return;
            }
            current.checkout(args[1], args[3]);
        } else {
            Message("Incorrect operands.");
        }
    }
    /** rm-branch command method.*/
    static void REMOVE_BRANCH(Gitlet current, String...args) {
        if (args.length != 2) {
            Message("Incorrect operands.");
            return;
        }
        current.rmBranch(args[1]);
    }
    /** reset command method.*/
    static void RESET(Gitlet current, String...args) {
        if (args.length != 2) {
            Message("Incorrect operands.");
            return;
        }
        current.reset(args[1]);
    }
    /** merge command method.*/
    static void MERGE(Gitlet current, String...args) {
        if (args.length != 2) {
            Message("Incorrect operands.");
            return;
        }
        if (current == null) {
            return;
        }
        current.merge(args[1]);
    }
}