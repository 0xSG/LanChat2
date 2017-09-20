/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lanchat2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 *
 * @author //// S G K //// 0xpulsar ////
 */
public class LanChat2 {

    ///// S G K /////
    public static String recvIpAddress;
    public static Scanner input;
    public static DatagramSocket skt;
    public static InetAddress recrIp;
    public static LanChatFrame lcf;
    
    
    public static void main(String[] args) throws SocketException, UnknownHostException {
        lcf=new LanChatFrame(); //Created the instance of the LanChatFram class
        lcf.setVisible(true);   //made it visible
        InetAddress addr = InetAddress.getLocalHost(); //getting out ip address
        lcf.setMyIpAddress(addr.toString()); //passed the string to the setMyIpAddress method in the LanChatFrame class
        // there it will be displayed on the outputbox
        
        skt = new DatagramSocket(5545); //setting the socket to 5545 . You can use any port number
        byte[] msgGotByte = new byte[1000];
        while (true) {//// S G K //// 0xpulsar ////
                    DatagramPacket gotMsgPkt = new DatagramPacket(msgGotByte, msgGotByte.length);
                    try {
                        skt.receive(gotMsgPkt);
                        System.out.println();
                        //System.out.println((gotMsgPkt.getAddress()).toString().substring(1) + ":" + (new String(gotMsgPkt.getData())).substring(0, gotMsgPkt.getLength()));
                        lcf.msgOut((gotMsgPkt.getAddress()).toString().substring(1) + ":" + (new String(gotMsgPkt.getData())).substring(0, gotMsgPkt.getLength()));
                    } catch (IOException ex) {/////0XPULSAR/////S G K //////
                        
                    }

                }
        
        
        
    }
    public static void setClientIP(String str) throws UnknownHostException{
        
        recvIpAddress=str;
         recrIp = InetAddress.getByName(recvIpAddress);
    }
    public static  void msgSend(String str) throws IOException{
        

            String yourMsg = str;
            
            byte[] buffer = new byte[yourMsg.length()];
            buffer = yourMsg.getBytes();//// S G K //// 0xpulsar ////
            DatagramPacket myMsgPkt = new DatagramPacket(buffer, buffer.length, recrIp, 5545);//CREATED THE PACKET
            skt.send(myMsgPkt);//SENDS THE DATA PACKET THROUGH THE SOCKET
            lcf.msgOut("You:"+yourMsg);//// S G K //// 0xpulsar ////
           
    }
    
    
}
