package utils;

import model.dao.*;
import model.server.*;
import vo.Card;

public class ObjectFactory {
	public static BaseDAO createDaoObject(String entityName) {
		if (entityName.toLowerCase().equals("carddao"))
			return new CardDao();
		if (entityName.toLowerCase().equals("studentdao"))
			return new StudentDao();
		if (entityName.toLowerCase().equals("financialrecorddao"))
			return new FinancialRecordDao();
		if (entityName.toLowerCase().equals("schoolrollinfodao"))
			return new SchoolRollInfoDao();
		if (entityName.toLowerCase().equals("transcriptdao"))
			return new TranscriptDao();
		if (entityName.toLowerCase().equals("physicalitemdao"))
			return new PhysicalItemDao();
		if (entityName.toLowerCase().equals("physicaltestdao"))
			return new PhysicalTestDao();
		if (entityName.toLowerCase().equals("chairuserecorddao"))
			return new ChairUseRecordDao();
		if (entityName.toLowerCase().equals("chairdao"))
			return new ChairDao();

		return null;
	}
	public static FinancialRecordModel createFinancialRecordModel(){
		return new FinancialRecordModelClass();
	}
	public static CardModel createCardModel() {
		return new CardModelClass();
	}
	public static CardModel createStudentModel() {
		return new StudentModelClass();
	}
	public static ChairUseRecordModel createChairUseRecordModel() {
		return new ChairUseRecordModelClass();
	}
	public static ChairModel createChairModel() {
		return new ChairModelClass();
	}

	public static PhysicalItemModel createPhysicalItemModel() {
		return new PhysicalItemModelClass();
	}
	public static PhysicalTestModel createPhysicalTestModel() {
		return new PhysicalTestModelClass();
	}
	public static SchoolAffairCenterModel createSchoolAffairCenterModel() {
		return new SchoolAffairCenterClass();
	}
	public static ManagerModelClass createManagerModel() {
		return new ManagerModelClass();
	}
	public static Card createCardVO(){
		return new vo.Card();
	}
}
