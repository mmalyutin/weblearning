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
	public Doc createDoc(String fileName, String mimeType, byte[] binaryData,
			User owner) {
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
		//TypedQuery<Doc> tquery = em.createQuery("from Doc d JOIN FETCH d.owner where d.id=:id", Doc.class);
		//tquery.setParameter("id", toRemove.getId());
		//Doc found = tquery.getSingleResult();
		
		Doc merged = em.merge(toRemove);
		System.out.println("merged: "+ merged.toString());
		if (merged != null) {
			System.out.println("About to remove merged object");
			merged.setOwner(null);
			em.remove(merged);
			System.out.println("After remove of merged object");
		}
	}

	@Override
	public List<Doc> listDocs(User owner) {
		TypedQuery<Doc> tquery = em.createQuery(
				"from Doc doc JOIN FETCH doc.owner", Doc.class);
		List<Doc> list = tquery.getResultList();
		return list;
	}

	@Override
	public Doc findDoc(Long id) {
		TypedQuery<Doc> tquery = em.createQuery(
				"from Doc doc where doc.id=:id", Doc.class);
		tquery.setParameter("id", id);
		Doc doc = tquery.getSingleResult();
		return doc;
	}

}
