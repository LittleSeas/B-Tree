package BtreeV2;


import java.util.*;


////// DisposeRoot ///////�е�key������Щ����

public class BTree {
  //���ڼ�¼ÿ���ڵ��еļ�ֵ����
  public int keyAmount;
  //���ĸ��ڵ�
  public Node root;

  public BTree(int keyAmount) {
    this.keyAmount = keyAmount;
    this.root = new Node(keyAmount);
  }



  //��B���в���Ҷ�ڵ�/////////////////////////////////////////////////////////////
  public void insert(long key,Object pointer)
  {
    //�ҵ�Ӧ�ò���Ľڵ㣬���ҵ�Ҷ�ӽڵ�
    Node theNode = search(key,root);

    //��Ҷ�ڵ����ҵ����пռ䣬�еĻ��ͰѼ���������
    if( !isFull(theNode) )
    {
      putKeyToNode(key,pointer,theNode);
    }else{
      //������ʵ���Ҷ�ڵ�û�пռ䣬�ͰѸ�Ҷ�ڵ���ѳ�����������ȷ�����ֵ
      Node newNode = separateLeaf(key,pointer,theNode);
      //������ѵ��Ǹ��ڵ㣬���½�һ���µĸ��ڵ㽫�½��Ľڵ���Ϊ���ĸ��ڵ�
	  //ԭ���ĸ��ڵ㣬�ͷ��ѵĽڵ�ֱ���Ϊ�¸��ڵ�����Һ��ӣ���ʱ�¸�ֻ��һ��Ԫ��
      if( isRoot(theNode) )
      {
        DisposeRoot(theNode,newNode,newNode.keys[0]);
      }else{
        //���½����Ľڵ��ָ����뵽�ϲ�ڵ�
        insertToInnerNode(theNode.parent,newNode,newNode.keys[0]);
      }
    }
  }




  //����Ѱ�Ҽ�ֵkey���ڵĻ�keyӦ�ò���Ľڵ�
  //keyΪ��ֵ,curNodeΪ��ǰ�ڵ�--һ���root�ڵ㿪ʼ
  public Node search(long key,Node curNode)
  {
    if (isLeaf(curNode))
      return curNode;

    for (int i = 0; i < this.keyAmount; i++)
    {
        if (key < curNode.keys[i]) //�ж��Ƿ��ǵ�һ��ֵ
          return search(key, (Node) curNode.pointer[i]);
        else if (key >= curNode.keys[i]) {
          if (i == curNode.keyAmount - 1) //�������û��ֵ
          {
            //���key�����һ����ֵ��,��������һ��ָ����еݹ��ѯ
            return search(key,(Node) curNode.pointer[curNode.keyAmount]);
          }
          else {
            if (key < curNode.keys[i + 1])
              return search(key, (Node) curNode.pointer[i + 1]);
          }
        }
    }
    //��ԶҲ���ᵽ������
    return null;
  }




	//�Ѽ�ֵ�ŵ�Ҷ�ڵ���--�������������Խ����////////////////////////////////////////
  private void putKeyToNode(long key,Object pointer,Node theNode)
  {
    int position = getInsertPosition(key,theNode);
    //���а�Ǩ����--------Ҷ�ڵ�İ�Ǩ
    if( isLeaf(theNode) )
    {
        if(theNode.keyAmount <= position)
        {
           theNode.add(key,pointer);
           return;
        }
        else{
            for (int j = theNode.keyAmount - 1; j >= position; j--) {
              theNode.keys[j + 1] = theNode.keys[j];
              theNode.pointer[j + 1] = theNode.pointer[j];
            }
            theNode.keys[position] = key;
            theNode.pointer[position] = pointer;
        }
     }else{
          //�ڲ��ڵ�İ�Ǩ----��һ���Ĳ�����ԣ�
          //ָ��Ĳ�������ݵĲ�����һλ
          for (int j = theNode.keyAmount - 1; j >= position; j--) {
            theNode.keys[j + 1] = theNode.keys[j];
            theNode.pointer[j + 2] = theNode.pointer[j+1];
          }
          theNode.keys[position] = key;
          theNode.pointer[position+1] = pointer;
        }
    //��ֵ������1
    theNode.keyAmount++;
  }



 //����Ӧ��Ҷ�ڵ���з��Ѳ���ȷ�����ֵ�������½��Ľڵ�///////////////////////////////
  private Node separateLeaf(long key,Object pointer,Node srcNode)
  {
    Node newNode = new Node(this.keyAmount);

    //�ֵܼ��ָ�봫��
    newNode.pointer[this.keyAmount] = srcNode.pointer[this.keyAmount];

    //��Ϊ����Node��Ԥ����һ��λ�����ڲ��룬������ĺ���(putKeyToLeaf())������Խ����
    //���Կ��Խ���-ָ����Ȳ��뵽Ԫ�ڵ㣬Ȼ���ٷֱ�ŵ������ڵ���
    putKeyToNode(key,pointer,srcNode);

    //��ǰ�ڵ����Ӧ����(n+1)/2ȡ�Ͻ������ֵ���
   // int oldNodeSize = (int)com.xuedi.maths.NumericalBound.getBound(0,(double)(this.keyAmount+1)/2);
    int oldNodeSize =(this.keyAmount+1)/2;


    for(int k = oldNodeSize; k <= this.keyAmount; k++)
    {
      newNode.add(srcNode.keys[k],srcNode.pointer[k]);
    }
    srcNode.keyAmount = oldNodeSize;

    //����ָ��--���½ڵ��Ϊ�ͽڵ���ұߵ��ֵ�
    srcNode.pointer[this.keyAmount] = newNode;

    return newNode;
  }




   //�½�һ���µĸ��ڵ㽫�½��Ľڵ���Ϊ�����ֽڵ�
  private void DisposeRoot(Node child1,Node child2,long key)
  {
        Node newRoot = new Node(this.keyAmount);
        newRoot.pointer[0] = child1;
        newRoot.pointer[1] = child2;
        newRoot.keyAmount = 1;
        newRoot.keys[0] = key;
        root = newRoot;
        //�������������Ҷ�ڵ������������������
        if( isLeaf(child1) )
        {
          //�ֵܼ��ָ�봫��
          child2.pointer[this.keyAmount] = child1.pointer[this.keyAmount];
          child1.pointer[this.keyAmount] = child2;
        }
        pointerRedirect(root);
        return;
  }





  //lowerNode���¼��ڵ������½������Ǹ��ڵ�///////////////////////////////////////
  //upperNode��lowerNode���ϲ�ڵ�
  private void insertToInnerNode(Node upperNode,Node lowerNode,long key)
  {
    //�ϲ�ڵ��п�λ��ֱ�Ӳ���
    if( !isFull(upperNode) )
    {
      putKeyToNode(key,lowerNode,upperNode);
      //���ø��ڵ�ָ��
      pointerRedirect(upperNode);
      return;
    }else{
      //������ѵ��Ǹ��ڵ㣬���½�һ���µĸ��ڵ㽫�½��Ľڵ���Ϊ�����ӽڵ�
      Node newNode;
      if( isRoot(upperNode) )
      {
        newNode = separateInnerNode(key,lowerNode,upperNode);
        Node newRoot = new Node(this.keyAmount);
        newRoot.pointer[0] = upperNode;
        newRoot.pointer[1] = newNode;
        upperNode.parent = newRoot;
        newNode.parent   = newRoot;
        newRoot.keyAmount = 1;
        //newRoot.keys[0] = key; 
		//Ӧ�ý��½ڵ����Сֵ�������ڵ�
		newRoot.keys[0]=newNode.keys[0];
        root = newRoot;
        //���ø��ڵ�ָ��
        pointerRedirect(upperNode);
        return;
      }else{
        //�ϲ�Ǹ��ڵ�û�п�λ���з��ѺͲ������
        newNode = separateInnerNode(key,lowerNode,upperNode);
        //���ø��ڵ�ָ��
        pointerRedirect(upperNode);

        //��¼Ҫ���ϲ���ļ�ֵ��Դ�ڵ��е�λ��(�ü�ֵ��separateInnerNode()��������srcNode��)
        int keyToUpperNodePosition = upperNode.keyAmount;
        //���ϵݹ����
        insertToInnerNode(upperNode.parent,newNode,upperNode.keys[keyToUpperNodePosition]);
        //���ø��ڵ�ָ��
        pointerRedirect(newNode);
      }

    }
  }



  //����Ӧ���ڲ��ڵ���з��Ѳ���ȷ�����ֵ�������½��Ľڵ�
  private Node separateInnerNode(long key,Object pointer,Node srcNode)
  {
    Node newNode = new Node(this.keyAmount);
   //��Ϊ����Node��Ԥ����һ��λ�����ڲ��룬������ĺ���(putKeyToLeaf())������Խ����
   //���Կ��Խ���-ָ����Ȳ��뵽Ԫ�ڵ㣬Ȼ���ٷֱ�ŵ������ڵ���
   putKeyToNode(key,pointer,srcNode);

   //��ǰ�ڵ���������(n+1)/2ȡ�Ͻ������ֵ���
  // int ptrSaveAmount = (int)com.xuedi.maths.NumericalBound.getBound(0,(double)(this.keyAmount+1)/2);
  // int keySaveAmount = (int)com.xuedi.maths.NumericalBound.getBound(0,(double)(this.keyAmount+1)/2);

   int ptrSaveAmount = (this.keyAmount+1)/2;
   int keySaveAmount = (this.keyAmount+1)/2;
  // int keyMoveAmount = (int)com.xuedi.maths.NumericalBound.getBound(1,(double)(this.keyAmount)/2);
   //(n+1)/2ȡ�Ͻ��ָ���n/2ȡ�Ͻ��������Դ�ڵ���
   //ʣ�µ�n+1)/2ȡ�½��ָn/2ȡ�½��������Դ�ڵ���
   for (int k = ptrSaveAmount; k < srcNode.keyAmount; k++) {
       newNode.add(srcNode.keys[k], srcNode.pointer[k]);
   }

   newNode.pointer[newNode.keyAmount] = srcNode.pointer[srcNode.pointer.length-1];
   

   srcNode.keyAmount = keySaveAmount;

	//���ø��ڵ�ָ��(�Ա㺢�ӽڵ�Ԫ�شﵽ���ֵ����ʱ���ҵ��ø��ڵ�)
   pointerRedirect(newNode);

   return newNode;
  }



 




  

  //�����ȷ�Ĳ���λ��
  private int getInsertPosition(long key,Node node)
  {
    //�����ݲ��뵽��Ӧ��λ��
    int position = 0;
    for (int i = 0; i < node.keyAmount; i++) {
        if (node.keys[i] > key)
          break;
        position++;
    }
    return position;
  }
 








	 //���õĸ�������////////////////////////////////////////////////////////////////
  //�ж�ĳ������Ƿ��Ѿ�װ����
 private boolean isFull(Node node)
 {
   if(node.keyAmount >= this.keyAmount)
     return true;
   else
     return false;
 }

 //�ж�ĳ���ڵ��Ƿ���Ҷ�ӽ��
 private  boolean isLeaf(Node node)
 {
   //int i = 0;
   if(node.keyAmount == 0)
     return true;

   //������µ�ָ����Node�ͣ���϶�����Ҷ�ӽڵ�
   if(node.pointer[0] instanceof Node)
     return false;

   return true;
 }

 private boolean isRoot(Node node)
 {
   if( node.equals(this.root) )
     return true;
   return false;
 }

 //���ڲ��ڵ��е��Լ������¶����Լ��ĸ���
  private void pointerRedirect(Node node)
  {
    for(int i = 0; i <= node.keyAmount; i++)
    {
      ((Node)node.pointer[i]).parent = node;
    }
  }


}