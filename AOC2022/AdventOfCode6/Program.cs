using System;
using System.Linq;

namespace AdventOfCode6
{
    class Program
    {
        static void Main(string[] args)
        {
            string all = "jfnjjwbbqttplpvllqgllmdllfmllscssqmqzmmwzznqnwqnwnqnjjbdbpbtbdbzzzljljzjjpccrmmppzfpzfpfnfccfbbcqcrcffblfbftbfbtbwwwmgwmgmnngnllnfllhghcghhjppchcfcnfffllmmqbmmpwwwwlqwwqgqcqsqjqpqzqqdzdtztltslsljjfqfcqqgbqqqghqgqvgvrggqwggrgjgmmnrmmzgmzgzpzjjctcmtcmcnndppcvpvrrwvrvhrvhrhjhnjnvjnjrjggccvffnqqvfqvvnmvmqmfmfqqzfzbfzzzgpzpllrwwnpwpnwnwgwhhrrdnrrdjjzjszsjjbddcdbbvmbmqbqnbqbsqbsqqwbwhwggssdnnmttvnnvmnmhmfhhjchcttzdzdqqszzcwwhhwzhwhphqhcqqsggddfmmvzmzwmwfwzwrrbmrrnwnfnwnlwnwrwfwnnmtnnzwnwdnnbhhrphrhlhwllpmmbcbtbffmqffddjnjwwzpzfpptbbqqwbwzbzjbjmjljblbtlblqqhqbqggrngrgllbmbccmhmqmqwwqcqssqzzfjzjrjnnqrqssfnsnvvtgvvmsvsqqljjbsbrrjllvfvzfzmzhzzhthjhshlslfljfjqjpqpvvmpmhpmhmqqmmdwmddppjlplhlsstlssgnggrbblggffcdfdzzwqqtztqtwqtwtzzsjsbszsbsvbvwwjqjnnpdpccwssvdsdzzqbqbtbtqtmtltltvlvddzwzzfpzpjpgphprpgpqqwppdwpdplddvffcdffvpvqqgvqgvvrfvrvqrrcjcpjjpttftqqvjqvqsvqsvqssdpdbbbmcmscsddbhhgttwhhjlltqllnqntqtsscnntwwhswswlwggldltlttsjszsnznsznzccbtbblplnnmfmqmrrvjvhjhzhnzzgnnhrrdrllblpbllfdfjjssvnssvlsllnqqhwqhhhsgstsjstthrrhrghrhhfmhmwhwrwwsrwrfwwdnntqnnsvnvmnnfvnntztqzqhqnqjnnjfflbfllrsllqhqdqccgvgnvvcwcfccmssqnqhhqrrfrtrvvnjnpjnjjpplmlppvmpphjhppvhvdvssjcjrrtrdrrsvvbbjzzrtztgzghzhccwmccshhzbhhdwdwsdswwlcwllpblpphrppfhfnffrbbcgcmggnvnzzmvvcrrftrftrffcscvcsslbljlglzgzbzczszmsmbmnbbhdhvvsqvqhvvfrfddbpwgvztwwqcpzhhwnhphnrwldjmztsptbbgsqbqqccwbdqzvhfjlfldgphzbfprclgpfztbrgvsvfpghmdchscbdqjqgzvmrtdrfzbhgdvgznjcsmglcfwhdtpsljnvvzjcbbrczwtgpdmgpzhctvbbmvsjzthffsjqhfsdrclpqslbhnmpczwvggpzbjcchfjzjhhgtrmlgnzlndfvzrccgggrpmprbmjbfjjhzrhrtwgqdbgdlqghssrnmtmpvttcqwnwdzhgfnddgbqcsdvzvwqdnmmpwrwhfbqtcpqhvwbczrmjqzsntvdrncwjsmvvwcngrtlwtjmnctwrrtvphbjhlqmgzfsfsrblzzvmzlbhzjhwbdfpncdrfchmrqhspdszcjrnvwtmjzmsmzcdphsdzjgqswwrpdvlpvrdnhplnlmswvcrzlcmbtqtscjfwrnrctrvdqcqzwcvgvpdgrndrgsrvzftwpqjjgjhzwhvrjlqntdtcjdrqzhqlqqdffcgvttlhvwgggnwmdlvghfgjpsmntbvbjbbttrwsljwsrvtmznvqdptpwtdcwtcsfdjlmdqthqggjcptrqhbsbjzqqmvvjmgmppqmjmnjdqvspzlbgzjsjshpslmszqnzghsszpsmpzfcrqqjdwvtbnzstvvjzvtzgpptcmvmbvmpvpzvgfnwtlmdzhvhshtwvnbgwmtzqhcptflpqsqvmptchpfcbwhvjzdcnsnqrgdwfcthqfssnbqnvgvvhlzqfqmdlcwnshtvhhhpghjbmhdbfbqcvbnbvwbzcbbmjnrqmsdqnmnbsrvhggzsrlbwtfmgwrnlhrbrrrqdcspnrpnppngrtdqtbmbhcbjrlhpfjpdnfndmqvwvhlgmsntpwrlrwwqhwvzbpzqqggnbqlsjjqtbqjcdpmndgmtdhfbqrpdzzsnmhzmqqnbdqftqmnhfbdzdlfwgjsjhrcsmtfzgwbvbbzdrlbmcgmppqfppmbqrnsmrmhrdsvgcfmzpfnvrbbgfccfcbphszwdbnnwcjjvvlpdtfzgtslvgqwmsvlpzjcbqwqclrjrsgthhtqrqrhvsdfjntgllsvslrvdtnsdmrgtqcmswnqwlrwlfmcfftbjpvdnmczqzldsssszhjtqtqvqtwhjcqchjvqvntvzzzprbmjcctsqfdcvpbtsgnnsqtqnmjhrgqcjnzrdsgrbtdpqjbgcmnfwhnsrfwcdmncjzwcngfbmmrsbvgvvqpvrdjfsqwjdmqjdpzcbjjfmzjjgbnwqgrvpmbzdhsgtldrzvglscfwbmjltcrzrgdslgprwscwbrhtdtglznjdcvfjzjjqzntdqdbcrcbbmvnzdshjzcsfsgpghmgdqdwsnwjtvtbqbqccbcwjpnhdhzcvdssvnvqtvzwprhpgftdwwvgsbnlzzjppcrrwmrsthvjjrvrsdrbdqfgsjsmwfplpstrbnpdhhcblhjfwzngmhlwbvnfcbgwshspsbbgbldrvmcnczszpgnddrfwrtgcqjggrrcbjwrdjlrvtspbftrtjbzjwchpfnjctcjtwtpmtblczcftqlphdjczfrvtzlsglpvhqsqqblttdjrlczhrqsgpggmvnhpqtrfbpgvzftwtsmwhwswtpvtwnsshmlcffpcjshqhqqsjtpbgszscmcbnhjjtjmpgfdhgmljqmmwlfptstjjvqhcbjpjpwzwqflhslclzzjlmcttbsncqmfzhgnzwbdtnvfwbtztwbhtfsqjfzwmfflmbwnqzqhcjwdpbvngsgzlwvwcqhqjsndznbbdcqqhmjjpqjbsnvwztgmqwdcbbjvcndmhsbvbjnzlbscmgnjcrrwrfdljtcsgmwtffgcjflpzzdcnzvmrbnrjbbmhzqqjtgsrwqmmrhpndwlbnrtrhhpqlmdrcrtdmzsslrmffpftdjvfcpvvhzhjhqtrrsclvtbsccgmmqrjbqgbmpnbzlsncssdhmjppjptvddfgbbnjzjjldjlqjzhhttsclrmsgzctwjqqvtjlfzwgtffgrdjzwdcnrprlcswffghngrqcgsbzqhhvbfjtwcjlrrmbtqjdrgpnbftnmzqnndnqwgrqndlwmjnnspbhjlnzrnptnrmcjhpbfcqpvbchvdwthjlcrfpssgtfbsgfrftcrwttrspbsvzpvcczmdqslcdgfljvtjsdpjnwmdvfzfllrdrbgvpltzlqcrlwbncswhfvrdthspmhfhfdlvpbcqlmjfznhnqblffftgzqrtswnmtnvjprqqhhhvrscvbbzgmnlnprghfdjqbgjppjzjrnclfdssbmgspwcscnlcrrqmtlljrmcwgdgcqwvvjzvsjdjvsspszlcthwzrwqtzdgmqvnlvvzrvrpqqwswzcchncrpnjdmflvmhhwvrrstpvnszfrmvpdtpqpbdmwvvbbpjnwmtststtlcvqdnvqqphzlhhzbbbjssgdcnhlmwrzwvwmcmgrcngqzcnffqzfnvldpdjmsspgpbrzhnszfnljfcrgsjvqjjbstvghlcslhqlzhltpglwffrzfgjghssfgrptbnpbhqnhhfbjsnmsvltqpthdmzzrhrhhmzlplvrtdqfrfrppdpqnllblcfjqpdwznsbrhcncdpmztcrjrfnlwtznrmpbzqsbrqrbnthgfpshrdhnwjmrnsmsfqwdjsmsvhfrbdpjrwcvmdvvmdtfqjgmdsrqtctsdmznngbsrfjvhllgwt";
            // string all = "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg"; // Testing case
            int len = all.Length;
            int i = 0;
            string current = "";
            int reqLen = 14;
            while (i < len)
            {
                /* if (all[i] != all[i + 1] && all[i] != all[i + 2] && all[i] != all[i + 3]
                    && all[i + 1] != all[i + 2] && all[i + 1] != all[i + 3]
                    && all[i + 2] != all[i + 3]) */

                current = all.Substring(i, reqLen);             
                if (isTrue(current))
                {
                    Console.WriteLine(i + reqLen);
                    break;
                }

                i++;
            }

        }
        static bool isTrue(string str)
        {
            // If at any time we encounter 2 same characters, return false
            for (int i = 0; i < str.Length; i++)
                for (int j = i + 1; j < str.Length; j++)
                    if (str[i] == str[j])
                        return false;

            // If no duplicate characters encountered, return true
            return true;
        }
    }
}
