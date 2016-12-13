package com.kingmed.bidir.service.soap;

import javax.jws.WebService;

@WebService(endpointInterface = "com.kingmed.bidir.service.soap.BidirService")
public class BidirServiceImpl implements BidirService {

	@Override
	public String getListRequest(String hospSampleID) {
		// TODO Auto-generated method stub
		return "getListRequest response ,hospSampleID = " + hospSampleID;
	}

	@Override
	public String affirmRequest(String hospSampleID, String itemCode) {
		// TODO Auto-generated method stub
		return "affirmRequest reponse, hospSampleID= " +hospSampleID + " itemCode =" + itemCode;
	}

	@Override
	public String uploadLisRepData(String resultXML) {
		// TODO Auto-generated method stub
		return "uploadLisRepData resultXML = " + resultXML;
	}

}
