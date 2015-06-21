package BtreeV2;



import java.util.Stack;

public class TreePrinter {

  public Node   root;
  private Stack  stack;
  public int layers = 0;

  public TreePrinter(Node root) {
  
    this.root  = root;
    stack = new Stack();
  }

  //��ӡһ����������
  private void printNode(Node node)
  {
    for(int i = 0; i < node.keyAmount; i++)
    {
      //frame.jTextArea1.append(node.keys[i]+",");
      System.out.print(node.keys[i]+",");
    }
  }

  //����һ��Ľڵ����ջ��---------���Ĺ�����ȱ���
  private void nextLayer()
  {
    Stack newStack = new Stack();
    Node  tempNode;

    //��һ�������ݵĻ������ջȻ�󷵻�
    if( !( ((Node)stack.get(0)).pointer[0] instanceof Node) )
    {
      stack.clear();
      return;
    }
    for(int i = 0; i < stack.size(); i++)
    {
      tempNode = (Node)stack.get(i);
      for(int j = 0; j <= tempNode.keyAmount; j++)
      {
        newStack.push(tempNode.pointer[j]);
      }
    }
    stack.clear();
    stack = newStack;
  }

  public void printAllTree()
  {
    stack.push(root);
    //����ײ�����
    while ( stack.size() != 0 ) {
      //frame.jTextArea1.append("layer" + layers+"\n");
      System.out.println("layer" + layers);

      Node curNode;
      for (int i = 0; i < stack.size(); i++) {
        //��ӡ��ǰ�ڵ�
        curNode = (Node)stack.get(i);
        printNode(curNode);
        //frame.jTextArea1.append("-->");
        System.out.print("-->");
      }
      //frame.jTextArea1.append("\n");
      System.out.print("\n");
      this.nextLayer();
      layers++;
    }
    layers--;
  }
}