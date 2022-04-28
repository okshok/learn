package learn.json.handler;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class NioTest {

	public static void main(String[] args) {
		Path path = Paths.get(System.getProperty("user.dir") + File.separator + "test.txt");
		FileSystem fileSystem = FileSystems.getDefault();


		try(FileChannel writeChannel = FileChannel.open(path, StandardOpenOption.CREATE, StandardOpenOption.WRITE )
		){
			int limit = 10_000_000;
			int size = 0;
			ByteBuffer buffer = ByteBuffer.allocate(1024);
			while (size < limit) {
				int value = size % 26 + 65;
				
				buffer.put(String.valueOf((char)value).getBytes());

				if(buffer.remaining() < 3) {
					// position 을 0으로 하고 limit을 현재 포지션으로 
					System.out.println("here");
					System.out.println(buffer.position() + " " +  buffer.limit() + " ");
					buffer.flip();
					System.out.println(buffer.position() + " " +  buffer.limit() + " ");
					writeChannel.write(buffer);
					System.out.println(buffer.position() + " " +  buffer.limit() + " ");
					// position 을 0으로 하고 limit을 capacity까지 최대로 할당
					buffer.clear();
					System.out.println(buffer.position() + " " +  buffer.limit() + " ");
				}
				
				size++;

			}
			buffer.flip();
			writeChannel.write(buffer);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
