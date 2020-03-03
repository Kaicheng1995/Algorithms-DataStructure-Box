/**
 * 依次找到某项后面最小的数字，并进行替换，替换后前面已替换过的内容不再改动
 * Step 1: create method that returns the index of the smallest element in Array after the index k element
 * Step 2: access to the smallest element and switch their locations one after another
 */

public class Selection_Sort{

    public static void main(String[] args){
        int[] InputArray = {3,0,4,6,3};
        for(int i = 0; i<InputArray.length; i++){
            System.out.print(Switch(InputArray)[i]);
        }
    }
    
    /**
     * It returns the index of the smallest element that occurs at or after index k in the array
     */
    public static int Smallest(int[] InputArray, int k){
        int x = InputArray[k];         // [起始位]：the (k)th item in Array
        int answer = k;                // [起始位index]：initialize the smallest element from k
        int index = k+1;               // [起始位后面第一位index]

        while (index < InputArray.length){        // index 的最大值也会比length少1，因为首位是0的关系
            if (InputArray[index] < x){           // if (k+1)th item < x
                x = InputArray[index];            // 如果if成立，那么 x 替换为目前为止的最小值
                answer = index;                   // 目前为止最小值的index
            }
            index += 1;                           // 继续往后
        }
        return answer;
    }

    /**
     * Given any array, the method Switch sorts the elements of the array in increasing order.
     */
    public static int[] Switch (int[] InputArray){
        int index = 0;
        while(index < InputArray.length){
            int target = Smallest(InputArray, index);  //从0位开始返回后面最小值的index
            int temp = InputArray[target];             //temp是当前最小值
            InputArray[target] = InputArray[index];    //位置替换
            InputArray[index] = temp;                  //位置替换
            index += 1;                                //依次往后重复
        }
        return InputArray;
    }
}
