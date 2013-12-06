package tk.sweetvvck.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import tk.sweetvvck.dao.StudentDao;
import tk.sweetvvck.domain.Student;
import tk.sweetvvck.exception.DaoException;
import tk.sweetvvck.utils.HibernateSessionFactory;

public class StudentDaoHibernateImpl extends BaseDaoHibernateImpl implements
		StudentDao {

	@Override
	public boolean addStudentInfo(Student student) {
		return super.add(student);
	}

	@Override
	public boolean deleteStudentInfo(Student student) {
		return super.delete(student);
	}

	@Override
	public boolean updateStudentInfo(Student student) {
		return super.update(student);
	}

	@Override
	public Student getStudentInfoByStudentName(String studentName) {
		Session session = HibernateSessionFactory.getSessionFactory()
				.getCurrentSession();
		Student student = null;
		Transaction tx = session.beginTransaction();
		
		try {
			Criteria criteria = session.createCriteria(Student.class);
			criteria.add(Restrictions.eq("studentName", studentName));
			
			student = (Student)criteria.uniqueResult();
			tx.commit();
		} catch (HibernateException e) {
			throw new DaoException(e.getMessage(),e);
		}
		return student;
	}

	@Override
	public Student getStudentInfoByStudentNum(String studentNum) {
		Session session = HibernateSessionFactory.getSessionFactory()
				.getCurrentSession();
		Student student = null;
		Transaction tx = session.beginTransaction();
		
		try {
			Criteria criteria = session.createCriteria(Student.class);
			criteria.add(Restrictions.eq("studentNum", studentNum));
			
			student = (Student)criteria.uniqueResult();
			tx.commit();
		} catch (HibernateException e) {
			throw new DaoException(e.getMessage(),e);
		}
		return student;
	}

	@Override
	public Student getStudentInfoByPhoneNum(String phoneNum) {
		Session session = HibernateSessionFactory.getSessionFactory()
				.getCurrentSession();
		Student student = null;
		Transaction tx = session.beginTransaction();
		
		try {
			Criteria criteria = session.createCriteria(Student.class);
			criteria.add(Restrictions.eq("phoneNum", phoneNum));
			
			student = (Student)criteria.uniqueResult();
			tx.commit();
		} catch (HibernateException e) {
			throw new DaoException(e.getMessage(),e);
		}
		return student;
	}

}
