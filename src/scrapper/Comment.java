/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package scrapper;

/**
 *
 * @author DiegoFernando
 */
public class Comment {
    String user = "";
    String time = "";
    String comment = "";
    Comment(String u, String t, String c){
        user = u;
        time = t;
        comment = c;
    }
}
