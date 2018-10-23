package main;

/**
 * Hello world!
 *
 */
public class App
{
	public static void main( String[] args )
	{
		if(!args.equals("")){
			for(String arg : args)
				System.out.println(arg);
		}

		System.out.println( "Hello World!" );
	}
}
