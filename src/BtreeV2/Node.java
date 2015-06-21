package BtreeV2;



import java.util.*;

public class Node {

  //父结点
  Node parent;
  //本层的所有节点
  long[] keys;
  //节点所含指针
  Object[] pointer;
  //该节点的键值数量
  int keyAmount;

  public Node(int size) {
    //键和指针对的数量都是n+1对于分裂时有帮助
	//多存一个键值位，方便节点并入时，将并入节点第一个元素插入被并入节点，无需越界检查
    this.keys   = new long[size+1];    
	//同理，多存一个指针，方便存储并入节点第一个元素后跟的指针
    this.pointer= new Object[size+2];
    this.keyAmount = 0;
    this.parent = null;
  }

  public Node(long[] newKey,Object[] newPointers)
  {
    this.keys = newKey;
    this.pointer = newPointers;
    this.keyAmount = newKey.length;
    this.parent = null;
  }

  //向节点中添加数据--返回添加的数据的数量--不进行越界检查
  //将检查交给外围函数处理
  public void add(long key,Object pointer)
  {
      keys[keyAmount] = key;
      this.pointer[keyAmount] = pointer;
      keyAmount++;
  }

}