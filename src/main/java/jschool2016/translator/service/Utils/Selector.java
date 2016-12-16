package jschool2016.translator.service.Utils;

import java.security.SecureRandom;
import java.util.List;
import java.util.Random;

import static java.lang.Math.toIntExact;

public class Selector {
    public static int[] selectPairs(int count, List<Long> priorities){
        int sum = 0;
        for (Long current : priorities){
            sum += current;
        }
        if (sum <= count) {//если требуемое количество слов больше размера словаря
            int[] res = new int[10];
            for (int i = 0; i < 10; i ++){
                res[i] = toIntExact(priorities.get(i));
            }
            return res;
        }
        else{
            int[] randomCountList = new int[10];
            Random random = new SecureRandom();
            for (int i = 0; i < count; i++){
                int number = getPropertyNumber(random.nextInt(55) + 1);
                randomCountList[number - 1]++;
            }
            //randomCountList содержит массив чисел, i-ый элемент - сколько слов в выборке должно быть с i-м приоритетом
            //но может получится так, что требуемое количество слов для какого-нибудь приоритета будет превышать количество слов с таким приоритетом
            //поэтому надо "подровнять"
            Long[] diff = new Long[10];
            for (int i = 0; i < 10; i ++){
                diff[i] = priorities.get(i) - randomCountList[i];
            }//diff содержит -n на позиции "дефицитного" приоритета
            for (int i = 0; i < 10; i ++){
                int j = 0;
                while ((j < 10) && ((diff[i] < 0))){
                    if (diff[j] > 0){
                        diff[j]--;
                        randomCountList[j]++;
                        randomCountList[i]--;
                        diff[i]++;
                    }
                    else
                        j++;
                }
            }
            return randomCountList;
        }
    }
    //Для рандомного числа от 1 до 55 считает номер приоритета (1-10), в соответствие с правилом, что i му приоритету подходят i чисел.
    //Таким образом, чем выше приоритет, тем больше шанс что это слово попадёт в выборку. для приоритета 1 подходит только число 1, а для приоритета 10 подходят числа 45-55
    private static int getPropertyNumber(int number){
        int i = 1;
        int f = 1;
        while ((i <= 10)&&(f < number)){
            i ++;
            f += i;
        }
        return i;
    }
}
