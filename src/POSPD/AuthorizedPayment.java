package POSPD;

public class AuthorizedPayment extends Payment 
{

	private String authorizationCode;

	public AuthorizedPayment()
	{
		
	}
	
	public String getAuthorizationCode() 
	{
		return this.authorizationCode;
	}

	public void setAuthorizationCode(String authorizationCode) 
	{
		this.authorizationCode = authorizationCode;
	}

	public Boolean isAuthorized() 
	{
		throw new UnsupportedOperationException();
	}
}
