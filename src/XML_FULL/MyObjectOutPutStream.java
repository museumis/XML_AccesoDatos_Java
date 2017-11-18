package XML_FULL;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class MyObjectOutPutStream extends ObjectOutputStream{

	public MyObjectOutPutStream(OutputStream arg0) throws IOException {
		super(arg0);
		
	}

	public MyObjectOutPutStream() throws IOException, SecurityException {
		super();
		
	}

	@Override
	protected void writeStreamHeader() throws IOException {
		
	}
	
	


	

}
