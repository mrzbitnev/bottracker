import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientConnection {
    boolean getConnection(byte[] magnetLink) {
        try (Socket socket = new Socket("localhost", 8675);
             DataOutputStream oos = new DataOutputStream(socket.getOutputStream());
             DataInputStream ois = new DataInputStream(socket.getInputStream())) {

            oos.write(magnetLink);
            oos.flush();
            System.out.println("Clien sent message " + magnetLink);

            String deliveryConfirm = ois.readUTF();
            System.out.println(deliveryConfirm);

            return deliveryConfirm.equals("Server got the link"); // if server get message - we'll get TRUE

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
