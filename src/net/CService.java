package net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class CService implements Runnable {
    private Socket mSocket; // Socket de service
    private BufferedReader mBr; // Flux d'entrée
    private PrintWriter mPw; // Flux de sortie
    private CServer mServer;
    
    
    public CService(CServer pServer, Socket pSocket) {
        mServer = pServer;
    	mSocket = pSocket;
    	try {
    		System.out.println("Service : création des flux.");
			mBr = new BufferedReader(new InputStreamReader(mSocket.getInputStream()));
			mPw = new PrintWriter(mSocket.getOutputStream(), true); // true = auto flush
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
    	new Thread(this).start();
    }

	@Override
	public void run() {
		while (true) {
			try {
				System.out.println("Service : lecture message du client.");
				String lRequest = mBr.readLine();
				System.out.println("Service : message reçu : " + lRequest);

				
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	public void sendMessage(String pMessage) {
		mPw.write(pMessage);
	}
}