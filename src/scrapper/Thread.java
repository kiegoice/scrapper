/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package scrapper;

import java.util.ArrayList;

/**
 *
 * @author DiegoFernando
 */
public class Thread {
    int id = 0;
    String status = "";
    String product = "";
    String component = "";
    String version = "";
    String importance = "";
    String assignedTo = "";
    String CClist = "";
    String reported = "";
    String modified = "";
    ArrayList<Comment> comments;
    Thread(int i, String s, String p, String c, ArrayList<Comment> co){
        id = i;
        status = s;
        product = p;
        component = c;
        comments = co;
    }
    
}
