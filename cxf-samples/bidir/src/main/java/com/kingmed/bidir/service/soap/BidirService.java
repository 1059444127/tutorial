package com.kingmed.bidir.service.soap;

import javax.jws.WebService;

@WebService
public interface BidirService {
	String getListRequest(String hospSampleID);

	String affirmRequest(String hospSampleID, String itemCode);

	String uploadLisRepData(String resultXML);
}
