package BtreeV2;



import java.util.*;

public class Node {

  //�����
  Node parent;
  //��������нڵ�
  long[] keys;
  //�ڵ�����ָ��
  Object[] pointer;
  //�ýڵ�ļ�ֵ����
  int keyAmount;

  public Node(int size) {
    //����ָ��Ե���������n+1���ڷ���ʱ�а���
	//���һ����ֵλ������ڵ㲢��ʱ��������ڵ��һ��Ԫ�ز��뱻����ڵ㣬����Խ����
    this.keys   = new long[size+1];    
	//ͬ�����һ��ָ�룬����洢����ڵ��һ��Ԫ�غ����ָ��
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

  //��ڵ����������--������ӵ����ݵ�����--������Խ����
  //����齻����Χ��������
  public void add(long key,Object pointer)
  {
      keys[keyAmount] = key;
      this.pointer[keyAmount] = pointer;
      keyAmount++;
  }

}