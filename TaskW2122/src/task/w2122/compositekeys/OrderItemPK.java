/*
 * Class to make a primary key for the entity OrderItems
 */
package task.w2122.compositekeys;

import java.io.Serializable;

@SuppressWarnings("serial")
public class OrderItemPK implements Serializable {

	public Long orderId;
	public Long itemId;

	public OrderItemPK(){

	}

	public OrderItemPK(Long orderId, Long itemId){
		this.orderId = orderId;
		this.itemId = itemId;
	}

	@Override
	public boolean equals(Object obj){
		boolean returnVal = false;

		if(null == obj){
			returnVal = false;
		}else if(!obj.getClass().equals(this.getClass())){
			returnVal = false;
		}else{
			OrderItemPK other = (OrderItemPK) obj;
			if(this == other){
				returnVal = true;
			}else if(orderId != null && other.orderId != null && this.orderId.equals(other.orderId)){
				if(itemId != null && other.itemId != null && this.itemId.equals(other.itemId)){
					returnVal = true;
				}
			}else{
				returnVal = false;
			}
		}
		return returnVal;
	}

	@Override
	public int hashCode(){

		if(orderId == null || itemId == null){
			return 0;
		}else{
			return orderId.hashCode() ^ itemId.hashCode();
		}
	}
}
