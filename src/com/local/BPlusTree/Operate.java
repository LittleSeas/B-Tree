package com.local.BPlusTree;

public interface Operate {
	public Object get(Comparable key); // ��ѯ

	public void remove(Comparable key); // �Ƴ�

	public void insertOrUpdate(Comparable key, Object obj); // ������߸��£�����Ѿ����ڣ��͸��£��������
}
