package Tarea08_LeecturaXML_STAX_Libros;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class MyObjectOutPutStream extends ObjectOutputStream{

	public MyObjectOutPutStream(OutputStream arg0) throws IOException {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public MyObjectOutPutStream() throws IOException, SecurityException {
		super();
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see java.io.ObjectOutputStream#writeStreamHeader()
	 */
	@Override
	protected void writeStreamHeader() throws IOException {
		
	}
	
	


	

}
