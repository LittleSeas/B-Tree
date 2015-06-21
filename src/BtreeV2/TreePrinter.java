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

  //打印一个结点的内容
  private void printNode(Node node)
  {
    for(int i = 0; i < node.keyAmount; i++)
    {
      //frame.jTextArea1.append(node.keys[i]+",");
      System.out.print(node.keys[i]+",");
    }
  }

  //将下一层的节点插入栈中---------树的广度优先遍历
  private void nextLayer()
  {
    Stack newStack = new Stack();
    Node  tempNode;

    //下一层是数据的话就清空栈然后返回
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
    //到达底部返回
    while ( stack.size() != 0 ) {
      //frame.jTextArea1.append("layer" + layers+"\n");
      System.out.println("layer" + layers);

      Node curNode;
      for (int i = 0; i < stack.size(); i++) {
        //打印当前节点
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