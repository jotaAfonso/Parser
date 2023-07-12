package types;


public class RefType implements IType {
	
	IType type;
	
	public RefType(IType type){
		this.type = type;
	}
	
	public String getType(){
		return "";
	}
	
	public boolean equalz(RefType otherRef){
		
		IType t1 = this.type, t2 = otherRef.type;
	
		if((t1 == IntType.singleton && t2 == IntType.singleton)
				||
			(t1 == BoolType.singleton && t2 == BoolType.singleton))
			return true;
		else{
			if(t1 instanceof RefType && t2 instanceof RefType)
				return t1.equals(t2);
			else
				return false;
			
		}
	}
	
}
