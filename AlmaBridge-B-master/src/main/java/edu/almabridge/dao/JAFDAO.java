package edu.almabridge.dao;

import java.util.List;

import edu.almabridge.model.JAF;

public interface JAFDAO {
	public boolean saveJAF(JAF jaf);
	public boolean removeJAF(int jafId);
	public JAF getJAF(int jafId);
	public List<JAF> JAFList(String userId);
	public List<JAF> signedJAFList(String userId);
	public List<JAF> notSignedJAFList(String userId);
	public boolean ifAlreadySigned(String userId, int jobId);

}
