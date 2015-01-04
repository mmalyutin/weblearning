package com.xzymon.weblearning.service;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

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

	@Override
	public void removeDoc(Doc toRemove) {
		em.remove(toRemove);
	}

	@Override
	public List<Doc> listDocs(User owner) {
		TypedQuery<Doc> tquery = em.createQuery("from Doc", Doc.class);
		List<Doc> list = tquery.getResultList();
		return list;
	}

	@Override
	public Doc findDoc(Long id) {
		TypedQuery<Doc> tquery = em.createQuery("from Doc doc where doc.id=:id", Doc.class);
		tquery.setParameter("id", id);
		Doc doc = tquery.getSingleResult();
		return doc;
	}

}
