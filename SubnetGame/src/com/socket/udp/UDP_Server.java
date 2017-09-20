package com.socket.udp;
import java.net.*;

public class UDP_Server
{
   @SuppressWarnings("resource")
public static void main(String args[]) throws Exception
      {
	   System.out.println("Server started! ");
	   	//creating a socket to connect to client
         DatagramSocket serverSocket = new DatagramSocket(9893);
            byte[] receiveData1 = new byte[1024];
            byte[] sendData;
            while(true)
               {
            	//Accept incoming packet at socket
                  DatagramPacket receivePacket = new DatagramPacket(receiveData1, receiveData1.length);
                  serverSocket.receive(receivePacket);
                  String str = new String( receivePacket.getData());
                  System.out.println("Data Received from client: " + str.trim());
                  
                  //process data to get the ip's and subnet mask
                  String str1[] = str.split("\\s+");
                  String ip1 = str1[0];
                  String ip2 = str1[1];
                  String mask = str1[2];
                  
                  //Get ip address and port of client
                  InetAddress IPAddress = receivePacket.getAddress();
                  int port = receivePacket.getPort();
                  
                  //logic to be performed
                  String s="";
                  try {
           			byte[] a1 = InetAddress.getByName(ip1).getAddress();
           		    byte[] a2 = InetAddress.getByName(ip2).getAddress();
           		    byte[] m = InetAddress.getByName(mask).getAddress();
           	
           		    boolean flag = false;
           		    for (int i = 0; i < a1.length; i++)
           		        if ((a1[i] & m[i]) != (a2[i] & m[i])) {
           		        	flag = true;
           		        	s = "The IP Addresses "+ip1+" and "+ip2+
           		        		  " belong to different network";
           		        }
           		    if(flag == false)
           		    	s = "The IP Addresses "+ip1+" and "+ip2+
      	        		  		" belong to same network";
           		}catch(UnknownHostException e) {
           			System.out.println("Incorrect IPs");
           		}
                  
                  //convert string/data to byte stream and send to the client
                  sendData = s.getBytes();
                  DatagramPacket sendPacket =
                  new DatagramPacket(sendData, sendData.length, IPAddress, port);
                  serverSocket.send(sendPacket);
                  System.out.println("Data sent to client:  " + s);
               }
      }
}