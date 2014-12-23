package com.xzymon.weblearning.service;

import java.io.InputStream;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.xzymon.weblearning.model.Doc;
import com.xzymon.weblearning.model.User;

@Stateless
@Local(UserOperationsLocal.class)
public class UserOperationsBean implements UserOperationsBusiness {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Doc createDoc(String fileName, String mimeType, byte[] binaryData, User owner) {
		Doc doc = new Doc();
		doc.setFileName(fileName);
		doc.setMimeType(mimeType);
		doc.setBinaryData(binaryData);
		doc.setFileLength(binaryData.length);
		doc.setOwner(owner);
		em.persist(doc);
		return doc;
	}

}
