/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */
package dodola.hotfix;

/**
* @author ly
* @created at 2016/7/27 16:26
* @des :
*/
public class LoadBugClass {
    public String getBugString() {
        BugClass bugClass = new BugClass();
        return bugClass.bug();
    }
}
