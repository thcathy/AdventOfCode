package com.adventofcode.year2022;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.HashSet;

import org.junit.Test;

public class Day6Part2 {
    final static int MARKER_DISTINCT_CHARACTERS = 14;
    final static String input = "tnmmpfmfzmmnsmsjmjjbvvhnhzzfmmgpmgpgbgnnwffjhffzqqmzzbnbssrqqrnnhsnngsszsqzszhzfhzfzwzfzrrmhmghgwhhjjqwqttwhttjllrtrtzzcfzfgzznfznfzfnnbddvmvzmmfsmfsmfffhlfldlqqrnrznnhmmgqqzhhmjhmhppqbpbbngnlldvvdqvvrtrdrtrnttnppfllrbbrprpnpdplpmllhwwddqpdprddzzfccqpcqpcpcbbdhdjdjwjcwcctdcttzgzmmscmsmdmttwhwzhhnjhnhlhvhlvlglpgpmmjmgmrgrddmwddjfftfwflfslffqtfqttpftppflfmmhvhvcvbvhbhggpbgbppvdpvpvfppbwwsnnhphllbdbnbvbmvvzffvsffdldmlmtmccnlnbnjbnjnhhbfhhgzzlwlfflzffdccggdcgcjjhffjfgfgcczjccvwcvvqgvvqvllqzqmqllhjjqnqggttsdddjgdjgjzzrgrfrbrssrgrgdgrgbbssmdsdfddsndnsdnsdnnmqqsspqqmrqqpmmsjmmszzqvqrvrzznnjdndtntfnttgtctqtwwnwswrrthrttsdttlhlvvdzzgqgttnppjpljplpgpvgvqqvppzmmqggtjgtgstslltjltjjgcjcmjmsshvvtppgmmlslqqshqshsllbggfpgffdsdgssncchctcwwtllgqlqblqlqvvmsvmmwnnzppqllsttgmttftvfvjjrzzswzzjvzjzljjchcshcscbbrdbrbcrrnvvtctntvtvbvjvqjqggsrspsprrbgghdghhmwwldldzdttrvrnrfftqtftrrdsszlzvvbtbffftzzrzqrrhjhghhwbhhsjsfsttdjdjnjhjmjpmplplrrdjdcdjdbjblllbqlqdlqlpqptppdhhqmqfqhqhchwwqjqfjqfqhqshsmswsbbvssdspdpsdssstntltrrgnnmttgmmsjjrlrnlrnrnwrwfwlfltlzllcjcmjcjpjhphcpcwppmvmjjzbzvbbfnfcflfddntddbmmmhnnsrnrrvdvnvcvwvcwvwrrqwqccqmmswmmjrjmmwjmjfjhwrtbjzdvlgrjmvzfmhcqsncvlhzzncjlbvcwrdwjmqjcnptqslvfzpsvltgzsvjdsjrppdrmqrbqwhddfhnftfblspsrhtdtjwdnhbcbtlwlvccsfscvczzrrqmwbwbdmwgzqntvflppqvppwrhnvtlsbzqglhsfdgssqzdtjdpwrrhbnbtwhhnmnlwfwlqffjjrndbpwwsvdrhddbjnnqzmtpvvtwbcpndjzlhcfrrdvmljswjzvmfqcdsgqwclqshwrmblszdvsnrpdgnllmlchzdjlrrpndmmgddjqgjqrhwfbwddqdfbvptrmzhtsqfsfswpnvmtswqprjhbzvntgrlzthhnqbtpplqpvcfnpgdtbhqbhflltbbtmmhcwztslmpznttmssclhmnbsbrwlblrbsdfmnpqbwwmsncvzmpqwhzjgcgdrzvglgdtswmstdhrprdjfmqtjlmplbjtzcgnrwpdvpfjjfwjfnnpmdtwtqsgfndngsbmcwjtglqwtfrclbczfcmjtgcwszhzrbcphrhwmhcwghjznzthnwpljjltdlvqtffsrbmwcsvrdmqqggbznnlzbbqtgspqvnjpbdhtzmgttrcwwszwpgdrcnfqtgrgqdrctlzwtdwqppbhnwgldnqltznnfpbfqtgmmwpcqnndbgmrrtgtvnmlfcwsldchjnnqfrhpzwtclrzftsqllgvpqbgmfjdhqjttwcvbpvfqsvhbhhtwnqnbgndbtzhcvgglbhghbzrbrmdllmgfgttqmhtdnwrpwllhnghrjctrbzrcpnjnctvmrlpjhftnfbczrjrnnbqplplcrbngbhvmmvcffmgvbhjzbhcmtwmwgmjmwjvvlqfldswpntjnsjvmdlbzqqlgbwspwvmnwtwjbczmwplrhmjgsppnmtwmvsfwnsgddgwqcvpftcpzrhpldnwmcjgtjmljjbcmjcqdbwczndnjnjgrmtjrqnnjndzqdqpcgdqptdbrqftnwrgqmrzrvsfmmmbpltlncvtgrjfjmvtgwqphczwjhdrdwtfvgztbhrndvpcbgfjfvmrrljwrvcrtdmtjndfnwgcnfrzgsnjpztbwwsbvqfnpjctgrhsflhnzbbsfqbnmtnvrmjzsbjfndvttpvpfjhqntflgbfnzcclcwmhbsgqfjdcgsvrhtstspfzgvgglgddqmclsmzgzgtncdsfmwdvtcsgwvbzjvclwppqdjgfcrcbzcwbdhrnssjbmnmfmwthdrnmlfhqlddwqrdhsdvdcsmcgjsgcmpnhlbnqftpdjswtmpbznlcrhtswgnmwjcdfmljdngzfsmlzjjnzmfzshmztdbdmcqwmlvcrzgpmbjqcghclwvdbrhgvwqchnndftnrtptmctdlhmfjvpzrpccddfpcdwmzqfhnsqzrvwblzfhcjdcjfctczwqrcbjnrpdcbbnsgnlvqqmnsfgsqschjlbzhhsrbvdbfrhvsgrlzwncgwpdbvmblgzbwbcbgqfwmdmgcrbbjfcvmqgztqpptdhwmvmsdqwplpgcjzgqzdrftzhqbltvhrmlrfffcgfpqzwrrbbtlsjgmtbjvtnmhwdpjptjwfwgjgvbfqwmflrrqzlzdcmtlnptdrpcpdnswcfscnndnrfbgwvvncdjgsdpbwptdtvrqlmrhmvvcwblhhzbjdpsbszhrftfbcgwhwrgglnjzqdhcqnvlhgqjhnddvrslhntssptsbhmqwwqqnbvfmcbgpvgjbrttnvlljdbtfplgmbwtcbcdtqdpqqdvhbmpmtszwpzblcfrtznhhtcljtdlhjdbnlhvwgjsmgvrslrfwnmzwlstpgltvrgnpdqztvfnvdhdtwwqdfsmtpbpdclsbnwcgjzchjcsjmvhbjshmjjlpgdzcgbmmchwmcsddsvhsnpqtcpnhqnbvwgwqhtjbqncgwwftnrzsbsjtvqmjzqvvncmncwflcfpcjqgdtbsmjzzsdjfvhnqbgjhmfgjghwscthbfmbndltbqzwpqtmrswvprpmgwqnqpfnmffrpdlpfqmhrthppzvzwbrtjvwvjndsqdlqtbpqwfcttggnjmcqqnmjwfhfjgcvlnmtlgbdvmctzlwbfgnflwtsflgnfbnfbhhdgjctzvvmrhdsmvmmtnqwtszmqcpsbrqrgjfrzctcbzmtdlhwjtfdqbtthdnqcrpwrhcrvjstbhpltvgmvpmvfjstgzjsgzprzcqzqztvvdcnrrqwrhddcrhhncdrlwzwqlnbbzcfmqtnwgfdscmrbwnbldlfrqchzdnlnmwncgrzdclnvcvplgwjsbzmbnnsdrsfhrlssvncnwmcrjdjbjpdtrrvlnbjvspfqbwdpcnnpjzfnmbhcdhlmdgbpvbzmfltzstnznfctcdzhbfsvnfbsjqzmwfllhtrsfghlrpjgrgzgchlwrdmqzbrncsvnwhfqmwjbnvjctzphcsftqsbmwntgvjqhhvwndvmfmjhhhmfdvrlhpvzmmhrbhbddqbdmgqqsvddsswmzqcjmvhztfqpchzpwhdshzjlmbmnsgzqhbnmrshwvtmgmgndtddpfwsjrrjdhncdhtlczdvlbvqplttnzrblthlcffdtfsdtpwzdgbldvnsttvpzmbgnqddrszftcpwrgmfzhjjvghpntmzcttcsnrjnfpqzqqqljhzlrpgwngllqjwnwfcsphqplgbzmfqfgbfsqpsrntszqbcqnhctsnbfshmlbwfflrwwsjwqwfqlgnftdwmctmclwjhjhbsspqldlshbmpbgrftpnbpsqldhrrbdqwfwvfhclrlfdjfmzgmptdjdcsplcspznfjrfhtsjndwpslrdgnllllwqjgznrhswfssdlvdpmwwgmstqbhfmdhtzvzzvhwzbrrvvsl";

    public static void main(String... args) throws IOException {
        Day6Part2 solution = new Day6Part2();
        solution.run();
    }

    void run() throws IOException {
        var result = positionAfterStartOfPacketMarker(input);
        System.out.println("How many characters need to be processed before the first start-of-packet marker is detected? " + result);
    }

    int positionAfterStartOfPacketMarker(String input) {
        for (int i = 0; i < input.length(); i++) {
            if (isMarker(input, i))
                return i + MARKER_DISTINCT_CHARACTERS;
        }
        throw new RuntimeException("cannot find marker");
    }

    boolean isMarker(String s, int position) {
        var chars = new HashSet<Character>();
        for (int i = 0; i < MARKER_DISTINCT_CHARACTERS; i++) chars.add(s.charAt(position + i));
        return chars.size() == MARKER_DISTINCT_CHARACTERS;
    }

    @Test
    public void unitTest() throws Exception {
        assertEquals(19, positionAfterStartOfPacketMarker("mjqjpqmgbljsphdztnvjfqwrcgsmlb"));
        assertEquals(23, positionAfterStartOfPacketMarker("bvwbjplbgvbhsrlpgdmjqwftvncz"));
    }
}