package types;

import java.util.List;

public class FunType implements IType {
	
	List<IType> types;
	
	public FunType(List<IType> types) {
		this.types = types;
	}
	
	public List<IType> getTypes() {
		return types;
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return null;
	}

}
