package com.xzymon.weblearning.service;

import java.io.InputStream;
import java.util.List;

import com.xzymon.weblearning.model.Doc;
import com.xzymon.weblearning.model.User;

public interface UserOperationsBusiness {
	Doc createDoc(String fileName, String mimeType, byte[] binaryData, User owner);
	void removeDoc(Doc toRemove);
	List<Doc> listDocs(User owner);
	Doc findDoc(Long id);
}
