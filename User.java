/** Represents a user in a social network. A user is characterized by a name,
 *  a list of user names that s/he follows, and the list's size. */
 public class User {

    // Maximum number of users that a user can follow
    static int maxfCount = 10;

    private String name;       // name of this user
    private String[] follows;  // array of user names that this user follows
    private int fCount;        // actual number of followees (must be <= maxfCount)

    /** Creates a user with an empty list of followees. */
    public User(String name) {
        this.name = name;
        follows = new String[maxfCount]; // fixed-size array for storing followees
        fCount = 0;                      // initial number of followees
    }

    /** Creates a user with some followees. The only purpose of this constructor is 
     *  to allow testing the toString and follows methods, before implementing other methods. */
    public User(String name, boolean gettingStarted) {
        this(name);
        follows[0] = "Foo";
        follows[1] = "Bar";
        follows[2] = "Baz";
        fCount = 3;
    }

    /** Returns the name of this user. */
    public String getName() {
        return name;
    }

    /** Returns the follows array. */
    public String[] getfFollows() {
        return follows;
    }

    /** Returns the number of users that this user follows. */
    public int getfCount() {
        return fCount;
    }

    /** If this user follows the given name, returns true; otherwise returns false. */
    public boolean follows(String name) {
        try {
            for (int i = 0; i < this.getfCount(); i++) {
                if (this.follows[i].equals(firstHighCase(name))) {
                    return true;
                }
            }
            return false;
        }
        catch (NullPointerException e){
            return false;
        }
    }
    /** Makes this user follow the given name. If successful, returns true. 
     *  If this user already follows the given name, or if the follows list is full, does nothing and returns false; */
    public boolean addFollowee(String name) {
        if (this.fCount < maxfCount && !this.follows(firstHighCase(name))) {
            this.follows[this.fCount++] = firstHighCase(name);   
            return true;
        }
        else {
            return false;
        } 
    }

    /** Removes the given name from the follows list of this user. If successful, returns true.
     *  If the name is not in the list, does nothing and returns false. */
    public boolean removeFollowee(String name) {
        for (int i = 0; i < this.fCount; i++) {
            if (firstHighCase(name).equals(this.follows[i])) {
                for (int j = i; j < fCount - 1; j++) {
                    this.follows[j] = this.follows[j + 1];
                }
                this.follows[--fCount] = null;
                return true;
            }
        }
        return false;
    }

    /** Counts the number of users that both this user and the other user follow.
    /*  Notice: This is the size of the intersection of the two follows lists. */
    public int countMutual(User other) {
        try {    
            int mutualFolloweesCount = 0;
            for (int i = 0; i < this.fCount; i++) {
                for (int j = 0; j < other.fCount; j++) {
                    if (this.follows[i].equals(other.follows[j])) {
                        mutualFolloweesCount++;
                        break;
                    }
                }
            }
            return mutualFolloweesCount;
        }
        catch (NullPointerException e) {
            return 0;
        }
    }

    /** Checks is this user is a friend of the other user.
     *  (if two users follow each other, they are said to be "friends.") */
    public boolean isFriendOf(User other) {
        for (int i = 0; i < this.fCount; i++) {
            if (this.follows[i].equals(other.name)) {
                for (int j = 0; j < other.fCount; j++) {
                    if (other.follows[j].equals(this.name)) {
                        return true;
                    }
                }
                break;
            }
        }
        return false;
    }

    /** Returns this user's name, and the names that s/he follows. */
    public String toString() {
        String ans = name + " -> ";
        for (int i = 0; i < fCount; i++) {
            ans = ans + follows[i] + " ";
        }
        return ans;
    }

    public static String firstHighCase(String str) {
        String otherStr = "";
        if (str.length() > 0) {
            if (str.charAt(0) < 123 && str.charAt(0) > 96) {
                otherStr += (char) (str.charAt(0) - 32);
                for (int i = 1; i < str.length(); i++) {
                    otherStr += str.charAt(i);
                }
                return otherStr;
            } else {
               return str;
            }
        }    
        return str;
    }
}
