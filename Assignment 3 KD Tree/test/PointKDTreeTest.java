import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import org.junit.Test;

/**
 * A class to test the working and methods of the PointKDTree class.
 */
public class PointKDTreeTest {

  @Test(expected = IllegalArgumentException.class)
  public void kdTreeTestCreationTestWithNullUsingConstructor() {
    SetOfPoints kdtree = new PointKDTree(null);
    assertEquals(kdtree.toString(), "");
  }

  @Test
  public void kdTreeCreationTestwithEmptyPointsUsingConstructor() {
    SetOfPoints kdtree = new PointKDTree(new ArrayList<Point2D>());
    assertEquals(kdtree.getPoints(), new ArrayList<Point2D>());
    assertEquals(kdtree.toString(), "This tree has no entry");
  }

  @Test
  public void kdTreeCreationTestWithOnePointUsingConstructor() {
    List<Point2D> points = new ArrayList<>();
    points.add(new Point2D(1, 2));
    SetOfPoints kdtree = new PointKDTree(points);
    assertEquals(kdtree.getPoints(), points);
    assertEquals(kdtree.toString(), "Leaf Node = [(x=1, y=2)]");
  }

  @Test
  public void kdTreeCreationTestWithManyPointsUsingConstructor() {
    List<Integer> xpoints = new ArrayList<>(
        List.of(296, 618, 734, 334, 868, 618, 996, 665, 476, 423, 431, 610, 931, 749, 943, 682, 218,
            758, 358, 564, 211, 168, 279, 25, 749, 427, 533, 26, 939, 477, 941, 671, 546, 961, 551,
            12, 813, 482, 338, 477, 811, 617, 77, 741, 354, 286, 549, 733, 557, 953, 831, 289, 553,
            550, 894, 352, 98, 293, 945, 812, 105, 941, 103, 619, 285, 487, 633, 141, 148, 880, 300,
            216, 687, 93, 482, 949, 871, 812, 237, 34, 806, 538, 943, 811, 616, 477, 23, 294, 484,
            427, 753, 369, 347, 150, 963, 34, 957, 287, 675, 209, 296, 765, 550, 20, 674, 821, 497,
            503, 286, 688, 947, 108, 433, 740, 78, 494, 425, 552, 754, 78, 423, 363, 413, 304, 901,
            942, 871, 346, 235, 544, 891, 93, 748, 224, 620, 501, 966, 953, 816, 221, 153, 568, 503,
            891, 39, 293, 229, 883, 375, 705, 755, 99, 494, 86, 371, 692, 358, 767, 881, 36, 837,
            826, 368, 940, 767, 430, 745, 297, 508, 362, 294, 875, 834, 350, 889, 499, 26, 310, 959,
            504, 425, 874, 177, 286, 619, 442, 951, 816, 148, 100, 291, 156, 161, 631, 370, 482,
            362, 414, 299, 959, 90, 41, 177, 820, 559, 749, 840, 620, 299, 353, 617, 959, 311, 773,
            43, 303, 419, 561, 700, 445, 816, 622, 508, 508, 286, 957, 833, 49, 316, 646, 39, 178,
            160, 158, 640, 553, 169, 91, 560, 423, 624, 434, 379, 830, 31, 231, 220, 251, 425, 967,
            250, 955, 891, 578, 683, 102, 305, 514, 449, 222, 307, 489, 240, 296, 566, 960, 489,
            233, 239, 158, 688, 889, 712, 832, 421, 116, 384, 98, 623, 102, 952, 43, 312, 29, 971,
            101, 646, 714, 966, 36, 243, 846, 240, 489, 183, 93, 433, 897, 824, 978, 955, 289, 764,
            240, 752, 91, 714, 301, 891, 445, 842, 635, 769, 752, 180, 649, 375, 91, 186, 585, 910,
            506, 767, 100, 901, 763, 636, 510, 159, 95, 692, 901, 579, 255, 975, 836, 957, 447, 707,
            715, 29, 626, 310, 363, 383, 625, 764, 713, 643, 373, 386, 378, 511, 358, 362, 907, 570,
            177, 187, 572, 434, 694, 51, 246, 164, 182, 234, 977, 585, 558, 979, 296, 851, 707, 702,
            632, 320, 845, 913, 786, 626, 517, 565, 121, 382, 242, 706, 368, 920, 505, 707, 176,
            109, 514, 302, 188, 563, 163, 828, 657, 109, 110, 514, 234, 445, 112, 383, 107, 388,
            712, 176, 37, 586, 246, 853, 242, 695, 977, 589, 184, 101, 915, 509, 179, 893, 712, 833,
            976, 501, 828, 848, 720, 109, 697, 298, 380, 328, 313, 704, 506, 591, 505, 178, 455,
            647, 370, 498, 372, 585, 310, 315, 895, 510, 104, 438, 912, 576, 720, 713, 51, 839, 526,
            300, 188, 249, 240, 265, 911, 921, 647, 190, 661, 837, 323, 510, 719, 648, 111, 325,
            971, 244, 834, 499, 971, 585, 846, 46, 308, 319, 63, 245, 847, 187, 369, 572, 236, 899,
            381, 465, 55, 45, 924, 324, 982, 655, 503, 267, 657, 770, 847, 772, 462, 779, 369, 526,
            859, 837, 966, 530, 193, 184, 119, 929, 587, 655, 923, 795, 508, 833, 399, 322, 67, 314,
            575, 653, 62, 437, 912, 392, 569, 256, 590, 857, 196, 372, 857, 255, 459, 915, 462, 931,
            53, 834, 583, 130, 248, 718, 448, 134, 906, 979, 575, 535, 398, 329, 647, 458, 263, 715,
            127, 249, 387, 537, 331, 724, 579, 110, 531, 190, 775, 0, 506, 864, 983, 207, 317, 60,
            452, 54, 982, 137, 252, 182, 385, 663, 979, 848, 863, 73, 65, 848, 473, 258, 456, 453,
            773, 519, 265, 642, 444, 998, 840, 659, 775, 659, 597, 974, 801, 841, 386, 641, 398,
            267, 847, 995, 673, 994, 331, 735, 337, 73, 140, 74, 666, 927, 199, 390, 13, 397, 915,
            590, 77, 601, 517, 275, 393, 649, 339, 780, 8, 710, 209, 738, 996, 742, 316, 249, 411,
            596, 856, 272, 191, 270, 987, 530, 919, 122, 595, 142, 790, 2, 199, 272, 460, 397, 797,
            463, 933, 919, 448, 521, 339, 341, 384, 914, 145, 928, 803, 979, 331, 794, 716, 341,
            859, 317, 0, 991, 599, 530, 535, 744, 989, 662, 981, 587, 334, 912, 985, 131, 733, 586,
            383, 667, 477, 546, 126, 596, 984, 453, 404, 792, 803, 808, 333, 723, 657, 995, 411,
            131, 10, 59, 670, 465, 594, 802, 808, 586, 997, 193, 133, 122, 791, 848, 454, 651, 719,
            998, 277, 56, 613, 61, 203, 138, 716, 283, 268, 213, 475, 792, 538, 931, 786, 603, 535,
            478, 650, 605, 69, 454, 16, 126, 999, 482, 990, 604, 784, 880, 785, 740, 7, 322, 612,
            524, 128, 14, 735, 401, 740, 192, 394, 868, 66, 277, 394, 799, 208, 728, 670, 352, 280,
            880, 863, 469, 746, 263, 946, 14, 995, 15, 221, 610, 75, 149, 604, 415, 527, 996, 728,
            874, 487, 730, 992, 84, 283, 679, 928, 136, 14, 401, 77, 267, 809, 345, 924, 946, 934,
            860, 879, 949, 82, 597, 880, 549, 811, 943, 934, 672, 802, 939, 606, 945, 334, 860, 408,
            261, 7, 260, 789, 353, 218, 273, 66, 941, 211, 21, 807, 673, 728, 809, 6, 527, 357, 415,
            553, 793, 669, 804, 467, 941, 285, 930, 138, 927, 880, 205, 416, 207, 466, 88, 400, 660,
            862, 479, 27, 597, 459, 462, 10, 72, 608, 944, 406, 222, 202, 924, 555, 215, 797, 488,
            616, 408, 615, 288, 337, 25, 937, 344, 592, 864, 684, 268, 886, 397, 726, 292, 335, 532,
            344, 73, 485, 9, 135, 69, 67, 792, 558, 595, 538, 492, 877, 820, 596, 871, 490, 616,
            216, 924, 405, 76, 955, 76, 802, 948, 472, 228, 926, 154, 740, 661, 347, 163, 860, 405,
            157, 267, 930, 938, 474, 738, 619, 217, 336, 799, 162, 155, 942, 202, 293, 671, 27, 543,
            13, 818, 224, 410, 541, 403, 424, 475, 70, 149, 532, 999, 811, 533, 875, 673, 155, 733,
            665));
    List<Integer> ypoints = new ArrayList<>(
        List.of(107, 368, 871, 983, 816, 376, 946, 968, 683, 278, 32, 628, 918, 416, 548, 447, 497,
            142, 260, 18, 724, 12, 668, 352, 436, 179, 991, 331, 701, 687, 640, 823, 606, 32, 454,
            784, 533, 554, 924, 712, 602, 472, 830, 728, 438, 507, 555, 996, 310, 322, 9, 430, 442,
            535, 112, 531, 215, 314, 583, 611, 1, 712, 68, 465, 583, 468, 38, 955, 739, 575, 123,
            680, 415, 399, 637, 496, 876, 657, 53, 202, 849, 966, 699, 701, 606, 821, 559, 353, 611,
            331, 467, 84, 767, 730, 105, 233, 292, 583, 844, 955, 308, 111, 632, 684, 890, 434, 240,
            54, 642, 471, 639, 24, 190, 913, 957, 351, 442, 603, 487, 963, 509, 321, 820, 104, 31,
            811, 964, 857, 205, 866, 349, 520, 698, 560, 573, 169, 94, 499, 651, 666, 727, 151, 118,
            381, 171, 490, 429, 635, 0, 10, 510, 370, 415, 776, 134, 425, 540, 153, 716, 288, 34,
            376, 238, 939, 160, 368, 843, 397, 2, 433, 494, 919, 144, 815, 501, 311, 638, 28, 395,
            164, 568, 988, 68, 789, 717, 62, 671, 761, 999, 441, 666, 757, 602, 370, 273, 897, 523,
            959, 431, 454, 770, 242, 126, 676, 586, 840, 72, 748, 461, 836, 848, 493, 102, 119, 222,
            356, 859, 553, 345, 58, 845, 717, 172, 185, 924, 606, 355, 85, 1, 12, 398, 189, 751,
            815, 210, 861, 485, 855, 655, 807, 721, 472, 135, 490, 684, 629, 971, 11, 762, 348, 47,
            722, 660, 123, 967, 546, 404, 69, 36, 930, 345, 847, 376, 689, 512, 587, 853, 597, 415,
            879, 834, 747, 91, 471, 926, 141, 26, 705, 816, 588, 863, 372, 233, 817, 287, 636, 126,
            67, 449, 607, 336, 75, 430, 904, 151, 893, 593, 547, 763, 87, 802, 968, 581, 442, 957,
            973, 98, 614, 757, 248, 229, 502, 447, 975, 280, 79, 383, 996, 99, 20, 191, 429, 531,
            728, 475, 661, 503, 313, 955, 892, 817, 485, 230, 36, 250, 464, 811, 238, 371, 126, 914,
            841, 400, 805, 187, 884, 673, 206, 329, 508, 108, 357, 331, 978, 856, 350, 558, 456,
            147, 506, 692, 825, 281, 382, 878, 322, 760, 255, 119, 961, 199, 899, 80, 448, 607, 730,
            167, 278, 219, 62, 928, 211, 772, 202, 303, 548, 502, 741, 15, 592, 477, 558, 588, 321,
            755, 196, 862, 974, 843, 3, 609, 579, 344, 834, 440, 527, 324, 692, 178, 374, 608, 825,
            191, 495, 110, 620, 915, 370, 112, 381, 906, 250, 548, 539, 934, 405, 750, 414, 804,
            911, 292, 164, 676, 883, 964, 478, 44, 510, 678, 672, 92, 712, 612, 220, 413, 812, 940,
            751, 293, 629, 480, 937, 588, 886, 776, 420, 597, 230, 450, 494, 646, 110, 976, 353,
            510, 791, 16, 471, 164, 473, 305, 40, 728, 280, 628, 294, 447, 712, 228, 683, 694, 836,
            982, 686, 368, 470, 695, 772, 433, 185, 688, 460, 440, 943, 797, 974, 902, 578, 29, 452,
            763, 139, 308, 391, 288, 906, 31, 234, 827, 489, 767, 137, 551, 977, 209, 129, 814, 913,
            98, 306, 585, 553, 20, 385, 328, 212, 103, 809, 974, 96, 436, 150, 686, 794, 428, 317,
            980, 593, 329, 992, 457, 345, 262, 274, 963, 269, 500, 321, 521, 229, 26, 620, 988, 582,
            292, 734, 500, 679, 175, 821, 607, 845, 39, 193, 285, 670, 389, 291, 615, 414, 738, 558,
            4, 246, 353, 765, 968, 211, 553, 850, 301, 999, 142, 556, 37, 723, 500, 638, 688, 597,
            171, 702, 828, 680, 254, 700, 665, 201, 115, 366, 670, 8, 536, 545, 643, 964, 648, 331,
            932, 927, 138, 943, 411, 913, 417, 292, 895, 114, 926, 703, 991, 333, 302, 761, 271, 16,
            309, 384, 151, 203, 201, 175, 175, 257, 360, 401, 628, 28, 413, 740, 576, 95, 236, 792,
            116, 558, 816, 186, 852, 212, 979, 136, 122, 317, 5, 924, 960, 34, 443, 577, 251, 720,
            322, 624, 456, 685, 819, 493, 200, 595, 452, 490, 275, 593, 501, 389, 504, 273, 708,
            973, 759, 257, 196, 911, 869, 135, 438, 218, 907, 515, 498, 873, 214, 545, 963, 551,
            552, 417, 509, 354, 20, 620, 518, 870, 796, 448, 963, 748, 602, 372, 833, 984, 375, 122,
            31, 763, 536, 799, 877, 349, 610, 276, 123, 514, 713, 711, 476, 150, 639, 294, 826, 320,
            531, 630, 327, 142, 884, 432, 781, 593, 936, 680, 962, 889, 927, 868, 411, 234, 941, 60,
            790, 485, 454, 971, 59, 525, 182, 253, 667, 350, 456, 857, 390, 452, 172, 984, 332, 565,
            919, 164, 852, 417, 61, 699, 377, 941, 18, 917, 267, 466, 947, 151, 837, 827, 266, 444,
            560, 293, 898, 780, 428, 714, 319, 788, 522, 416, 684, 439, 59, 243, 76, 603, 529, 135,
            772, 80, 300, 610, 274, 35, 265, 471, 232, 465, 183, 810, 609, 726, 300, 10, 671, 742,
            224, 199, 213, 686, 663, 349, 642, 448, 702, 287, 338, 824, 144, 517, 764, 175, 55, 309,
            729, 148, 170, 241, 247, 527, 457, 523, 372, 456, 187, 697, 777, 455, 916, 600, 953,
            939, 120, 209, 552, 829, 331, 434, 181, 393, 453, 800, 340, 657, 894, 20, 274, 94, 846,
            595, 506, 715, 358, 215, 700, 676, 796, 205, 650, 256, 595, 761, 192, 760, 893, 783,
            369, 46, 808, 992, 900, 576, 704, 473, 298, 593, 154, 775, 921, 78, 378, 768, 109, 238,
            542, 270, 168, 698, 131, 533, 484, 990, 752, 189, 797, 73, 901, 942, 62, 781, 819, 508,
            719, 238, 658, 848, 847, 909, 963, 27, 929, 653, 32, 386, 108, 909, 580, 104, 296, 409,
            992, 700, 660, 35, 661, 683, 255, 677, 56, 946, 307, 578, 979, 475, 36, 960, 733, 229,
            916, 843, 596, 645, 654, 248, 428, 836, 822, 93, 311, 491, 907, 134, 705, 192, 587, 637,
            262, 244, 626, 666, 853, 203, 675, 943, 542, 960, 820, 506, 932, 571, 692, 370, 887,
            949));
    List<Point2D> points = new ArrayList<>();
    for (int i = 0; i < xpoints.size(); i++) {
      points.add(new Point2D(xpoints.get(i), ypoints.get(i)));
    }
    SetOfPoints kdtree = new PointKDTree(points);
    assertTrue(new HashSet<>(kdtree.getPoints()).equals(new HashSet<>(points)));
    System.out.println(kdtree);
  }

  @Test
  public void kdTreeCreationUsingAddTestwithEmptyPointsUsingConstructor() {
    SetOfPoints kdtree = new PointKDTree(new ArrayList<Point2D>());
    kdtree.add(new Point2D(1, 2));
    assertEquals(kdtree.getPoints(), new ArrayList<Point2D>(List.of(new Point2D(1, 2))));
    assertEquals(kdtree.toString(), "Leaf Node = [(x=1, y=2)]");
  }

  @Test
  public void kdTreeCreationUsingAddTestWithOnePointUsingConstructor() {
    List<Point2D> points = new ArrayList<>();
    points.add(new Point2D(1, 2));
    SetOfPoints kdtree = new PointKDTree(points);
    kdtree.add(new Point2D(3, 4));
    assertEquals(kdtree.getPoints(),
        new ArrayList<Point2D>(List.of(new Point2D(1, 2), new Point2D(3, 4))));
    assertEquals(kdtree.toString(), "Group Node = [(x=1, y=2)]\n"
        + "Left Division =null\n"
        + "Right Division = Leaf Node = [(x=3, y=4)]");
  }

  @Test
  public void kdTreeCreationUsingAddTestWithCouplePointUsingConstructor() {
    List<Point2D> points = new ArrayList<>();
    SetOfPoints kdtree = new PointKDTree(points);
    kdtree.add(new Point2D(1, 2));
    kdtree.add(new Point2D(3, 4));
    kdtree.add(new Point2D(1, 1));
    kdtree.add(new Point2D(0, 1));
    assertTrue(new HashSet<>(kdtree.getPoints()).equals(
        new HashSet<>(new ArrayList<Point2D>(List.of(new Point2D(1, 2),
            new Point2D(3, 4), new Point2D(1, 1), new Point2D(0, 1))))));
    assertEquals(kdtree.toString(), "Group Node = [(x=1, y=2), (x=1, y=1)]\n"
        + "Left Division =Leaf Node = [(x=0, y=1)]\n"
        + "Right Division = Leaf Node = [(x=3, y=4)]");
  }

  @Test
  public void kdTreeCreationUsingAddTestWithManyPointsUsingConstructor() {
    List<Integer> xpoints = new ArrayList<>(
        List.of(296, 618, 734, 334, 868, 618, 996, 665, 476, 423, 431, 610, 931, 749, 943, 682, 218,
            758, 358, 564, 211, 168, 279, 25, 749, 427, 533, 26, 939, 477, 941, 671, 546, 961, 551,
            12, 813, 482, 338, 477, 811, 617, 77, 741, 354, 286, 549, 733, 557, 953, 831, 289, 553,
            550, 894, 352, 98, 293, 945, 812, 105, 941, 103, 619, 285, 487, 633, 141, 148, 880, 300,
            216, 687, 93, 482, 949, 871, 812, 237, 34, 806, 538, 943, 811, 616, 477, 23, 294, 484,
            427, 753, 369, 347, 150, 963, 34, 957, 287, 675, 209, 296, 765, 550, 20, 674, 821, 497,
            503, 286, 688, 947, 108, 433, 740, 78, 494, 425, 552, 754, 78, 423, 363, 413, 304, 901,
            942, 871, 346, 235, 544, 891, 93, 748, 224, 620, 501, 966, 953, 816, 221, 153, 568, 503,
            891, 39, 293, 229, 883, 375, 705, 755, 99, 494, 86, 371, 692, 358, 767, 881, 36, 837,
            826, 368, 940, 767, 430, 745, 297, 508, 362, 294, 875, 834, 350, 889, 499, 26, 310, 959,
            504, 425, 874, 177, 286, 619, 442, 951, 816, 148, 100, 291, 156, 161, 631, 370, 482,
            362, 414, 299, 959, 90, 41, 177, 820, 559, 749, 840, 620, 299, 353, 617, 959, 311, 773,
            43, 303, 419, 561, 700, 445, 816, 622, 508, 508, 286, 957, 833, 49, 316, 646, 39, 178,
            160, 158, 640, 553, 169, 91, 560, 423, 624, 434, 379, 830, 31, 231, 220, 251, 425, 967,
            250, 955, 891, 578, 683, 102, 305, 514, 449, 222, 307, 489, 240, 296, 566, 960, 489,
            233, 239, 158, 688, 889, 712, 832, 421, 116, 384, 98, 623, 102, 952, 43, 312, 29, 971,
            101, 646, 714, 966, 36, 243, 846, 240, 489, 183, 93, 433, 897, 824, 978, 955, 289, 764,
            240, 752, 91, 714, 301, 891, 445, 842, 635, 769, 752, 180, 649, 375, 91, 186, 585, 910,
            506, 767, 100, 901, 763, 636, 510, 159, 95, 692, 901, 579, 255, 975, 836, 957, 447, 707,
            715, 29, 626, 310, 363, 383, 625, 764, 713, 643, 373, 386, 378, 511, 358, 362, 907, 570,
            177, 187, 572, 434, 694, 51, 246, 164, 182, 234, 977, 585, 558, 979, 296, 851, 707, 702,
            632, 320, 845, 913, 786, 626, 517, 565, 121, 382, 242, 706, 368, 920, 505, 707, 176,
            109, 514, 302, 188, 563, 163, 828, 657, 109, 110, 514, 234, 445, 112, 383, 107, 388,
            712, 176, 37, 586, 246, 853, 242, 695, 977, 589, 184, 101, 915, 509, 179, 893, 712, 833,
            976, 501, 828, 848, 720, 109, 697, 298, 380, 328, 313, 704, 506, 591, 505, 178, 455,
            647, 370, 498, 372, 585, 310, 315, 895, 510, 104, 438, 912, 576, 720, 713, 51, 839, 526,
            300, 188, 249, 240, 265, 911, 921, 647, 190, 661, 837, 323, 510, 719, 648, 111, 325,
            971, 244, 834, 499, 971, 585, 846, 46, 308, 319, 63, 245, 847, 187, 369, 572, 236, 899,
            381, 465, 55, 45, 924, 324, 982, 655, 503, 267, 657, 770, 847, 772, 462, 779, 369, 526,
            859, 837, 966, 530, 193, 184, 119, 929, 587, 655, 923, 795, 508, 833, 399, 322, 67, 314,
            575, 653, 62, 437, 912, 392, 569, 256, 590, 857, 196, 372, 857, 255, 459, 915, 462, 931,
            53, 834, 583, 130, 248, 718, 448, 134, 906, 979, 575, 535, 398, 329, 647, 458, 263, 715,
            127, 249, 387, 537, 331, 724, 579, 110, 531, 190, 775, 0, 506, 864, 983, 207, 317, 60,
            452, 54, 982, 137, 252, 182, 385, 663, 979, 848, 863, 73, 65, 848, 473, 258, 456, 453,
            773, 519, 265, 642, 444, 998, 840, 659, 775, 659, 597, 974, 801, 841, 386, 641, 398,
            267, 847, 995, 673, 994, 331, 735, 337, 73, 140, 74, 666, 927, 199, 390, 13, 397, 915,
            590, 77, 601, 517, 275, 393, 649, 339, 780, 8, 710, 209, 738, 996, 742, 316, 249, 411,
            596, 856, 272, 191, 270, 987, 530, 919, 122, 595, 142, 790, 2, 199, 272, 460, 397, 797,
            463, 933, 919, 448, 521, 339, 341, 384, 914, 145, 928, 803, 979, 331, 794, 716, 341,
            859, 317, 0, 991, 599, 530, 535, 744, 989, 662, 981, 587, 334, 912, 985, 131, 733, 586,
            383, 667, 477, 546, 126, 596, 984, 453, 404, 792, 803, 808, 333, 723, 657, 995, 411,
            131, 10, 59, 670, 465, 594, 802, 808, 586, 997, 193, 133, 122, 791, 848, 454, 651, 719,
            998, 277, 56, 613, 61, 203, 138, 716, 283, 268, 213, 475, 792, 538, 931, 786, 603, 535,
            478, 650, 605, 69, 454, 16, 126, 999, 482, 990, 604, 784, 880, 785, 740, 7, 322, 612,
            524, 128, 14, 735, 401, 740, 192, 394, 868, 66, 277, 394, 799, 208, 728, 670, 352, 280,
            880, 863, 469, 746, 263, 946, 14, 995, 15, 221, 610, 75, 149, 604, 415, 527, 996, 728,
            874, 487, 730, 992, 84, 283, 679, 928, 136, 14, 401, 77, 267, 809, 345, 924, 946, 934,
            860, 879, 949, 82, 597, 880, 549, 811, 943, 934, 672, 802, 939, 606, 945, 334, 860, 408,
            261, 7, 260, 789, 353, 218, 273, 66, 941, 211, 21, 807, 673, 728, 809, 6, 527, 357, 415,
            553, 793, 669, 804, 467, 941, 285, 930, 138, 927, 880, 205, 416, 207, 466, 88, 400, 660,
            862, 479, 27, 597, 459, 462, 10, 72, 608, 944, 406, 222, 202, 924, 555, 215, 797, 488,
            616, 408, 615, 288, 337, 25, 937, 344, 592, 864, 684, 268, 886, 397, 726, 292, 335, 532,
            344, 73, 485, 9, 135, 69, 67, 792, 558, 595, 538, 492, 877, 820, 596, 871, 490, 616,
            216, 924, 405, 76, 955, 76, 802, 948, 472, 228, 926, 154, 740, 661, 347, 163, 860, 405,
            157, 267, 930, 938, 474, 738, 619, 217, 336, 799, 162, 155, 942, 202, 293, 671, 27, 543,
            13, 818, 224, 410, 541, 403, 424, 475, 70, 149, 532, 999, 811, 533, 875, 673, 155, 733,
            665));
    List<Integer> ypoints = new ArrayList<>(
        List.of(107, 368, 871, 983, 816, 376, 946, 968, 683, 278, 32, 628, 918, 416, 548, 447, 497,
            142, 260, 18, 724, 12, 668, 352, 436, 179, 991, 331, 701, 687, 640, 823, 606, 32, 454,
            784, 533, 554, 924, 712, 602, 472, 830, 728, 438, 507, 555, 996, 310, 322, 9, 430, 442,
            535, 112, 531, 215, 314, 583, 611, 1, 712, 68, 465, 583, 468, 38, 955, 739, 575, 123,
            680, 415, 399, 637, 496, 876, 657, 53, 202, 849, 966, 699, 701, 606, 821, 559, 353, 611,
            331, 467, 84, 767, 730, 105, 233, 292, 583, 844, 955, 308, 111, 632, 684, 890, 434, 240,
            54, 642, 471, 639, 24, 190, 913, 957, 351, 442, 603, 487, 963, 509, 321, 820, 104, 31,
            811, 964, 857, 205, 866, 349, 520, 698, 560, 573, 169, 94, 499, 651, 666, 727, 151, 118,
            381, 171, 490, 429, 635, 0, 10, 510, 370, 415, 776, 134, 425, 540, 153, 716, 288, 34,
            376, 238, 939, 160, 368, 843, 397, 2, 433, 494, 919, 144, 815, 501, 311, 638, 28, 395,
            164, 568, 988, 68, 789, 717, 62, 671, 761, 999, 441, 666, 757, 602, 370, 273, 897, 523,
            959, 431, 454, 770, 242, 126, 676, 586, 840, 72, 748, 461, 836, 848, 493, 102, 119, 222,
            356, 859, 553, 345, 58, 845, 717, 172, 185, 924, 606, 355, 85, 1, 12, 398, 189, 751,
            815, 210, 861, 485, 855, 655, 807, 721, 472, 135, 490, 684, 629, 971, 11, 762, 348, 47,
            722, 660, 123, 967, 546, 404, 69, 36, 930, 345, 847, 376, 689, 512, 587, 853, 597, 415,
            879, 834, 747, 91, 471, 926, 141, 26, 705, 816, 588, 863, 372, 233, 817, 287, 636, 126,
            67, 449, 607, 336, 75, 430, 904, 151, 893, 593, 547, 763, 87, 802, 968, 581, 442, 957,
            973, 98, 614, 757, 248, 229, 502, 447, 975, 280, 79, 383, 996, 99, 20, 191, 429, 531,
            728, 475, 661, 503, 313, 955, 892, 817, 485, 230, 36, 250, 464, 811, 238, 371, 126, 914,
            841, 400, 805, 187, 884, 673, 206, 329, 508, 108, 357, 331, 978, 856, 350, 558, 456,
            147, 506, 692, 825, 281, 382, 878, 322, 760, 255, 119, 961, 199, 899, 80, 448, 607, 730,
            167, 278, 219, 62, 928, 211, 772, 202, 303, 548, 502, 741, 15, 592, 477, 558, 588, 321,
            755, 196, 862, 974, 843, 3, 609, 579, 344, 834, 440, 527, 324, 692, 178, 374, 608, 825,
            191, 495, 110, 620, 915, 370, 112, 381, 906, 250, 548, 539, 934, 405, 750, 414, 804,
            911, 292, 164, 676, 883, 964, 478, 44, 510, 678, 672, 92, 712, 612, 220, 413, 812, 940,
            751, 293, 629, 480, 937, 588, 886, 776, 420, 597, 230, 450, 494, 646, 110, 976, 353,
            510, 791, 16, 471, 164, 473, 305, 40, 728, 280, 628, 294, 447, 712, 228, 683, 694, 836,
            982, 686, 368, 470, 695, 772, 433, 185, 688, 460, 440, 943, 797, 974, 902, 578, 29, 452,
            763, 139, 308, 391, 288, 906, 31, 234, 827, 489, 767, 137, 551, 977, 209, 129, 814, 913,
            98, 306, 585, 553, 20, 385, 328, 212, 103, 809, 974, 96, 436, 150, 686, 794, 428, 317,
            980, 593, 329, 992, 457, 345, 262, 274, 963, 269, 500, 321, 521, 229, 26, 620, 988, 582,
            292, 734, 500, 679, 175, 821, 607, 845, 39, 193, 285, 670, 389, 291, 615, 414, 738, 558,
            4, 246, 353, 765, 968, 211, 553, 850, 301, 999, 142, 556, 37, 723, 500, 638, 688, 597,
            171, 702, 828, 680, 254, 700, 665, 201, 115, 366, 670, 8, 536, 545, 643, 964, 648, 331,
            932, 927, 138, 943, 411, 913, 417, 292, 895, 114, 926, 703, 991, 333, 302, 761, 271, 16,
            309, 384, 151, 203, 201, 175, 175, 257, 360, 401, 628, 28, 413, 740, 576, 95, 236, 792,
            116, 558, 816, 186, 852, 212, 979, 136, 122, 317, 5, 924, 960, 34, 443, 577, 251, 720,
            322, 624, 456, 685, 819, 493, 200, 595, 452, 490, 275, 593, 501, 389, 504, 273, 708,
            973, 759, 257, 196, 911, 869, 135, 438, 218, 907, 515, 498, 873, 214, 545, 963, 551,
            552, 417, 509, 354, 20, 620, 518, 870, 796, 448, 963, 748, 602, 372, 833, 984, 375, 122,
            31, 763, 536, 799, 877, 349, 610, 276, 123, 514, 713, 711, 476, 150, 639, 294, 826, 320,
            531, 630, 327, 142, 884, 432, 781, 593, 936, 680, 962, 889, 927, 868, 411, 234, 941, 60,
            790, 485, 454, 971, 59, 525, 182, 253, 667, 350, 456, 857, 390, 452, 172, 984, 332, 565,
            919, 164, 852, 417, 61, 699, 377, 941, 18, 917, 267, 466, 947, 151, 837, 827, 266, 444,
            560, 293, 898, 780, 428, 714, 319, 788, 522, 416, 684, 439, 59, 243, 76, 603, 529, 135,
            772, 80, 300, 610, 274, 35, 265, 471, 232, 465, 183, 810, 609, 726, 300, 10, 671, 742,
            224, 199, 213, 686, 663, 349, 642, 448, 702, 287, 338, 824, 144, 517, 764, 175, 55, 309,
            729, 148, 170, 241, 247, 527, 457, 523, 372, 456, 187, 697, 777, 455, 916, 600, 953,
            939, 120, 209, 552, 829, 331, 434, 181, 393, 453, 800, 340, 657, 894, 20, 274, 94, 846,
            595, 506, 715, 358, 215, 700, 676, 796, 205, 650, 256, 595, 761, 192, 760, 893, 783,
            369, 46, 808, 992, 900, 576, 704, 473, 298, 593, 154, 775, 921, 78, 378, 768, 109, 238,
            542, 270, 168, 698, 131, 533, 484, 990, 752, 189, 797, 73, 901, 942, 62, 781, 819, 508,
            719, 238, 658, 848, 847, 909, 963, 27, 929, 653, 32, 386, 108, 909, 580, 104, 296, 409,
            992, 700, 660, 35, 661, 683, 255, 677, 56, 946, 307, 578, 979, 475, 36, 960, 733, 229,
            916, 843, 596, 645, 654, 248, 428, 836, 822, 93, 311, 491, 907, 134, 705, 192, 587, 637,
            262, 244, 626, 666, 853, 203, 675, 943, 542, 960, 820, 506, 932, 571, 692, 370, 887,
            949));
    SetOfPoints kdtree = new PointKDTree(new ArrayList<Point2D>());
    for (int i = 0; i < xpoints.size(); i++) {
      kdtree.add(new Point2D(xpoints.get(i), ypoints.get(i)));
    }
    List<Point2D> pointsToBeInTree = new ArrayList<>();
    for (int i = 0; i < xpoints.size(); i++) {
      pointsToBeInTree.add(new Point2D(xpoints.get(i), ypoints.get(i)));
    }
    assertTrue(new HashSet<>(kdtree.getPoints()).equals(new HashSet<>(pointsToBeInTree)));
  }

  @Test
  public void kdTreeCreationUsingTwoPointsOnSameLine() {
    List<Point2D> points = new ArrayList<>();
    SetOfPoints kdtree1 = new PointKDTree(points);
    kdtree1.add(new Point2D(10, 2));
    kdtree1.add(new Point2D(10, 4));
    assertTrue(new HashSet<>(kdtree1.getPoints()).equals(
        new HashSet<>(new ArrayList<Point2D>(List.of(new Point2D(10, 2),
            new Point2D(10, 4))))));
    assertEquals(kdtree1.toString(), "Group Node = [(x=10, y=2), (x=10, y=4)]\n"
        + "Left Division =null\n"
        + "Right Division = null");
    points.add(new Point2D(10, 2));
    points.add(new Point2D(10, 4));
    SetOfPoints kdtree2 = new PointKDTree(points);
    assertTrue(new HashSet<>(kdtree2.getPoints()).equals(
        new HashSet<>(new ArrayList<Point2D>(List.of(new Point2D(10, 2),
            new Point2D(10, 4))))));
    assertEquals(kdtree2.toString(), "Group Node = [(x=10, y=2), (x=10, y=4)]\n"
        + "Left Division =null\n"
        + "Right Division = null");
  }

  @Test
  public void kdTreeCreationUsingFourPointsWithHierarchySplitOnLeftRight() {
    List<Point2D> points = new ArrayList<>();
    SetOfPoints kdtree1 = new PointKDTree(points);
    kdtree1.add(new Point2D(10, 2));
    kdtree1.add(new Point2D(10, 4));
    kdtree1.add(new Point2D(7, 4));
    kdtree1.add(new Point2D(15, 4));

    assertTrue(new HashSet<>(kdtree1.getPoints()).equals(
        new HashSet<>(new ArrayList<Point2D>(List.of(new Point2D(10, 2),
            new Point2D(10, 4), new Point2D(7, 4), new Point2D(15, 4))))));
    assertEquals(kdtree1.toString(), "Group Node = [(x=10, y=2), (x=10, y=4)]\n"
        + "Left Division =Leaf Node = [(x=7, y=4)]\n"
        + "Right Division = Leaf Node = [(x=15, y=4)]");
    points.add(new Point2D(10, 2));
    points.add(new Point2D(10, 4));
    points.add(new Point2D(7, 4));
    points.add(new Point2D(15, 4));
    SetOfPoints kdtree2 = new PointKDTree(points);
    assertTrue(new HashSet<>(kdtree2.getPoints()).equals(
        new HashSet<>(new ArrayList<Point2D>(List.of(new Point2D(10, 2),
            new Point2D(10, 4), new Point2D(7, 4), new Point2D(15, 4))))));
    assertEquals(kdtree2.toString(), "Group Node = [(x=10, y=2), (x=10, y=4)]\n"
        + "Left Division =Leaf Node = [(x=7, y=4)]\n"
        + "Right Division = Leaf Node = [(x=15, y=4)]");
  }

  @Test
  public void kdTreeCreationUsingFourPointsWithHierarchySplitOnLeft() {
    List<Point2D> points = new ArrayList<>();
    SetOfPoints kdtree1 = new PointKDTree(points);
    kdtree1.add(new Point2D(10, 2));
    kdtree1.add(new Point2D(10, 4));
    kdtree1.add(new Point2D(7, 4));
    kdtree1.add(new Point2D(7, 4));

    assertTrue(new HashSet<>(kdtree1.getPoints()).equals(
        new HashSet<>(new ArrayList<Point2D>(List.of(new Point2D(10, 2),
            new Point2D(10, 4), new Point2D(7, 4), new Point2D(7, 4))))));
    assertEquals(kdtree1.toString(), "Group Node = [(x=10, y=2), (x=10, y=4)]\n"
        + "Left Division =Group Node = [(x=7, y=4), (x=7, y=4)]\n"
        + "Left Division =null\n"
        + "Right Division = null\n"
        + "Right Division = null");
    points.add(new Point2D(10, 2));
    points.add(new Point2D(10, 4));
    points.add(new Point2D(7, 4));
    points.add(new Point2D(7, 4));
    SetOfPoints kdtree2 = new PointKDTree(points);
    assertTrue(new HashSet<>(kdtree2.getPoints()).equals(
        new HashSet<>(new ArrayList<Point2D>(List.of(new Point2D(10, 2),
            new Point2D(10, 4), new Point2D(7, 4), new Point2D(7, 4))))));
    assertEquals(kdtree2.toString(), "Group Node = [(x=10, y=2), (x=10, y=4)]\n"
        + "Left Division =Group Node = [(x=7, y=4), (x=7, y=4)]\n"
        + "Left Division =null\n"
        + "Right Division = null\n"
        + "Right Division = null");
  }

  @Test
  public void kdTreeCreationUsingFourPointsWithHierarchySplitOnRight() {
    List<Point2D> points = new ArrayList<>();
    SetOfPoints kdtree1 = new PointKDTree(points);
    kdtree1.add(new Point2D(10, 2));
    kdtree1.add(new Point2D(10, 4));
    kdtree1.add(new Point2D(12, 4));
    kdtree1.add(new Point2D(12, 4));

    assertTrue(new HashSet<>(kdtree1.getPoints()).equals(
        new HashSet<>(new ArrayList<Point2D>(List.of(new Point2D(10, 2),
            new Point2D(10, 4), new Point2D(12, 4), new Point2D(12, 4))))));
    assertEquals(kdtree1.toString(), "Group Node = [(x=10, y=2), (x=10, y=4)]\n"
        + "Left Division =null\n"
        + "Right Division = Group Node = [(x=12, y=4), (x=12, y=4)]\n"
        + "Left Division =null\n"
        + "Right Division = null");
    points.add(new Point2D(10, 2));
    points.add(new Point2D(10, 4));
    points.add(new Point2D(12, 4));
    points.add(new Point2D(12, 4));
    SetOfPoints kdtree2 = new PointKDTree(points);
    assertTrue(new HashSet<>(kdtree2.getPoints()).equals(
        new HashSet<>(new ArrayList<Point2D>(List.of(new Point2D(10, 2),
            new Point2D(10, 4), new Point2D(12, 4), new Point2D(12, 4))))));
    assertEquals(kdtree2.toString(), "Group Node = [(x=12, y=4), (x=12, y=4)]\n"
        + "Left Division =Group Node = [(x=10, y=4)]\n"
        + "Left Division =Leaf Node = [(x=10, y=2)]\n"
        + "Right Division = null\n"
        + "Right Division = null");
  }

  @Test(expected = IllegalArgumentException.class)
  public void kdTreeCreationTestUsingNullsInListOnConstructor() {
    List<Point2D> points = new ArrayList<>();
    points.add(null);
    points.add(null);
    new PointKDTree(points);
  }

  @Test
  public void kdTreeCreationTestUsingNullsInListOnConstructorWithSomeValidPoint() {
    List<Point2D> points = new ArrayList<>();
    points.add(null);
    points.add(null);
    points.add(new Point2D(10, 2));
    points.add(new Point2D(10, 4));
    points.add(new Point2D(12, 4));
    points.add(new Point2D(12, 4));
    SetOfPoints kdtree2 = new PointKDTree(points);
    assertTrue(new HashSet<>(kdtree2.getPoints()).equals(
        new HashSet<>(new ArrayList<Point2D>(List.of(new Point2D(10, 2),
            new Point2D(10, 4), new Point2D(12, 4), new Point2D(12, 4))))));
    assertEquals(kdtree2.toString(), "Group Node = [(x=12, y=4), (x=12, y=4)]\n"
        + "Left Division =Group Node = [(x=10, y=4)]\n"
        + "Left Division =Leaf Node = [(x=10, y=2)]\n"
        + "Right Division = null\n"
        + "Right Division = null");
  }

  @Test
  public void kdTreeCreationUsingMultiplePointsOnTopNode() {
    List<Point2D> points = new ArrayList<>();
    SetOfPoints kdtree1 = new PointKDTree(points);
    kdtree1.add(new Point2D(10, 1));
    kdtree1.add(new Point2D(10, 2));
    kdtree1.add(new Point2D(10, 3));
    kdtree1.add(new Point2D(10, 4));

    assertTrue(new HashSet<>(kdtree1.getPoints()).equals(
        new HashSet<>(new ArrayList<Point2D>(List.of(new Point2D(10, 1),
            new Point2D(10, 2), new Point2D(10, 3), new Point2D(10, 4))))));
    assertEquals(kdtree1.toString(),
        "Group Node = [(x=10, y=1), (x=10, y=2), (x=10, y=3), (x=10, y=4)]\n"
            + "Left Division =null\n"
            + "Right Division = null");
    points.add(new Point2D(10, 1));
    points.add(new Point2D(10, 2));
    points.add(new Point2D(10, 3));
    points.add(new Point2D(10, 4));
    SetOfPoints kdtree2 = new PointKDTree(points);
    assertTrue(new HashSet<>(kdtree2.getPoints()).equals(
        new HashSet<>(new ArrayList<Point2D>(List.of(new Point2D(10, 1),
            new Point2D(10, 2), new Point2D(10, 3), new Point2D(10, 4))))));
    assertEquals(kdtree2.toString(),
        "Group Node = [(x=10, y=1), (x=10, y=2), (x=10, y=3), (x=10, y=4)]\n"
            + "Left Division =null\n"
            + "Right Division = null");
  }

  @Test
  public void kdTreeCreationTestusingAddCheckStepbyStep() {
    List<Point2D> points = new ArrayList<>();
    points.add(new Point2D(5, 8));
    SetOfPoints kdtree = new PointKDTree(points);
    assertEquals(kdtree.toString(), "Leaf Node = [(x=5, y=8)]");
    kdtree.add(new Point2D(5, 12));
    assertEquals(kdtree.toString(), "Group Node = [(x=5, y=8), (x=5, y=12)]\n"
        + "Left Division =null\n"
        + "Right Division = null");
    kdtree.add(new Point2D(4, 8));
    assertEquals(kdtree.toString(), "Group Node = [(x=5, y=8), (x=5, y=12)]\n"
        + "Left Division =Leaf Node = [(x=4, y=8)]\n"
        + "Right Division = null");
    kdtree.add(new Point2D(7, 10));
    assertEquals(kdtree.toString(), "Group Node = [(x=5, y=8), (x=5, y=12)]\n"
        + "Left Division =Leaf Node = [(x=4, y=8)]\n"
        + "Right Division = Leaf Node = [(x=7, y=10)]");
    kdtree.add(new Point2D(4, 8));
    assertEquals(kdtree.toString(), "Group Node = [(x=5, y=8), (x=5, y=12)]\n"
        + "Left Division =Group Node = [(x=4, y=8), (x=4, y=8)]\n"
        + "Left Division =null\n"
        + "Right Division = null\n"
        + "Right Division = Leaf Node = [(x=7, y=10)]");
    kdtree.add(new Point2D(7, 10));
    assertEquals(kdtree.toString(), "Group Node = [(x=5, y=8), (x=5, y=12)]\n"
        + "Left Division =Group Node = [(x=4, y=8), (x=4, y=8)]\n"
        + "Left Division =null\n"
        + "Right Division = null\n"
        + "Right Division = Group Node = [(x=7, y=10), (x=7, y=10)]\n"
        + "Left Division =null\n"
        + "Right Division = null");
    kdtree.add(new Point2D(3, 4));
    assertEquals(kdtree.toString(), "Group Node = [(x=5, y=8), (x=5, y=12)]\n"
        + "Left Division =Group Node = [(x=4, y=8), (x=4, y=8)]\n"
        + "Left Division =Leaf Node = [(x=3, y=4)]\n"
        + "Right Division = null\n"
        + "Right Division = Group Node = [(x=7, y=10), (x=7, y=10)]\n"
        + "Left Division =null\n"
        + "Right Division = null");
    kdtree.add(new Point2D(2, 3));
    assertEquals(kdtree.toString(), "Group Node = [(x=5, y=8), (x=5, y=12)]\n"
        + "Left Division =Group Node = [(x=4, y=8), (x=4, y=8)]\n"
        + "Left Division =Group Node = [(x=3, y=4)]\n"
        + "Left Division =Leaf Node = [(x=2, y=3)]\n"
        + "Right Division = null\n"
        + "Right Division = null\n"
        + "Right Division = Group Node = [(x=7, y=10), (x=7, y=10)]\n"
        + "Left Division =null\n"
        + "Right Division = null");
    kdtree.add(new Point2D(7, 9));
    assertEquals(kdtree.toString(), "Group Node = [(x=5, y=8), (x=5, y=12)]\n"
        + "Left Division =Group Node = [(x=4, y=8), (x=4, y=8)]\n"
        + "Left Division =Group Node = [(x=3, y=4)]\n"
        + "Left Division =Leaf Node = [(x=2, y=3)]\n"
        + "Right Division = null\n"
        + "Right Division = null\n"
        + "Right Division = Group Node = [(x=7, y=10), (x=7, y=10)]\n"
        + "Left Division =Leaf Node = [(x=7, y=9)]\n"
        + "Right Division = null");
    kdtree.add(new Point2D(7, 11));
    assertEquals(kdtree.toString(), "Group Node = [(x=5, y=8), (x=5, y=12)]\n"
        + "Left Division =Group Node = [(x=4, y=8), (x=4, y=8)]\n"
        + "Left Division =Group Node = [(x=3, y=4)]\n"
        + "Left Division =Leaf Node = [(x=2, y=3)]\n"
        + "Right Division = null\n"
        + "Right Division = null\n"
        + "Right Division = Group Node = [(x=7, y=10), (x=7, y=10)]\n"
        + "Left Division =Leaf Node = [(x=7, y=9)]\n"
        + "Right Division = Leaf Node = [(x=7, y=11)]");
  }

  @Test
  public void kdTreeCreationTestusingAddCheckStepbyStepWithNegativeValues() {
    List<Point2D> points = new ArrayList<>();
    points.add(new Point2D(-5, -8));
    SetOfPoints kdtree = new PointKDTree(points);
    assertEquals(kdtree.toString(), "Leaf Node = [(x=-5, y=-8)]");
    kdtree.add(new Point2D(-5, -12));
    assertEquals(kdtree.toString(), "Group Node = [(x=-5, y=-8), (x=-5, y=-12)]\n"
        + "Left Division =null\n"
        + "Right Division = null");
    kdtree.add(new Point2D(-4, -8));
    assertEquals(kdtree.toString(), "Group Node = [(x=-5, y=-8), (x=-5, y=-12)]\n"
        + "Left Division =null\n"
        + "Right Division = Leaf Node = [(x=-4, y=-8)]");
    kdtree.add(new Point2D(-7, -10));
    assertEquals(kdtree.toString(), "Group Node = [(x=-5, y=-8), (x=-5, y=-12)]\n"
        + "Left Division =Leaf Node = [(x=-7, y=-10)]\n"
        + "Right Division = Leaf Node = [(x=-4, y=-8)]");
    kdtree.add(new Point2D(-4, -8));
    assertEquals(kdtree.toString(), "Group Node = [(x=-5, y=-8), (x=-5, y=-12)]\n"
        + "Left Division =Leaf Node = [(x=-7, y=-10)]\n"
        + "Right Division = Group Node = [(x=-4, y=-8), (x=-4, y=-8)]\n"
        + "Left Division =null\n"
        + "Right Division = null");
    kdtree.add(new Point2D(-7, -10));
    assertEquals(kdtree.toString(), "Group Node = [(x=-5, y=-8), (x=-5, y=-12)]\n"
        + "Left Division =Group Node = [(x=-7, y=-10), (x=-7, y=-10)]\n"
        + "Left Division =null\n"
        + "Right Division = null\n"
        + "Right Division = Group Node = [(x=-4, y=-8), (x=-4, y=-8)]\n"
        + "Left Division =null\n"
        + "Right Division = null");
    kdtree.add(new Point2D(-3, -4));
    assertEquals(kdtree.toString(), "Group Node = [(x=-5, y=-8), (x=-5, y=-12)]\n"
        + "Left Division =Group Node = [(x=-7, y=-10), (x=-7, y=-10)]\n"
        + "Left Division =null\n"
        + "Right Division = null\n"
        + "Right Division = Group Node = [(x=-4, y=-8), (x=-4, y=-8)]\n"
        + "Left Division =null\n"
        + "Right Division = Leaf Node = [(x=-3, y=-4)]");
    kdtree.add(new Point2D(-2, -3));
    assertEquals(kdtree.toString(), "Group Node = [(x=-5, y=-8), (x=-5, y=-12)]\n"
        + "Left Division =Group Node = [(x=-7, y=-10), (x=-7, y=-10)]\n"
        + "Left Division =null\n"
        + "Right Division = null\n"
        + "Right Division = Group Node = [(x=-4, y=-8), (x=-4, y=-8)]\n"
        + "Left Division =null\n"
        + "Right Division = Group Node = [(x=-3, y=-4)]\n"
        + "Left Division =null\n"
        + "Right Division = Leaf Node = [(x=-2, y=-3)]");
    kdtree.add(new Point2D(-7, -9));
    assertEquals(kdtree.toString(), "Group Node = [(x=-5, y=-8), (x=-5, y=-12)]\n"
        + "Left Division =Group Node = [(x=-7, y=-10), (x=-7, y=-10)]\n"
        + "Left Division =null\n"
        + "Right Division = Leaf Node = [(x=-7, y=-9)]\n"
        + "Right Division = Group Node = [(x=-4, y=-8), (x=-4, y=-8)]\n"
        + "Left Division =null\n"
        + "Right Division = Group Node = [(x=-3, y=-4)]\n"
        + "Left Division =null\n"
        + "Right Division = Leaf Node = [(x=-2, y=-3)]");
    kdtree.add(new Point2D(-7, -11));
    assertEquals(kdtree.toString(), "Group Node = [(x=-5, y=-8), (x=-5, y=-12)]\n"
        + "Left Division =Group Node = [(x=-7, y=-10), (x=-7, y=-10)]\n"
        + "Left Division =Leaf Node = [(x=-7, y=-11)]\n"
        + "Right Division = Leaf Node = [(x=-7, y=-9)]\n"
        + "Right Division = Group Node = [(x=-4, y=-8), (x=-4, y=-8)]\n"
        + "Left Division =null\n"
        + "Right Division = Group Node = [(x=-3, y=-4)]\n"
        + "Left Division =null\n"
        + "Right Division = Leaf Node = [(x=-2, y=-3)]");
  }

  @Test(expected = IllegalArgumentException.class)
  public void closestPointWithNoOnePointInTree() {
    SetOfPoints kdtree = new PointKDTree(null);
    Point2D closestPoint = kdtree.closestPoint(new Point2D(-10, 50));
    assertEquals(closestPoint, new Point2D(-10, 50));
  }

  @Test
  public void closestPointWithOnlYOnePointInTree() {
    SetOfPoints kdtree = new PointKDTree(new ArrayList<>(List.of(new Point2D(10, 20))));
    Point2D closestPoint = kdtree.closestPoint(new Point2D(-10, 50));
    assertEquals(closestPoint, new Point2D(10, 20));
  }

  @Test
  public void closestPointWithMultiplePointsInTree() {
    List<Point2D> points = new ArrayList<>();
    points.add(new Point2D(10, 20));
    points.add(new Point2D(-1, 10));
    points.add(new Point2D(5, 3));
    points.add(new Point2D(-5, -10));
    points.add(new Point2D(100, 100));
    points.add(new Point2D(-100, -100));

    SetOfPoints kdtree = new PointKDTree(points);
    assertEquals(kdtree.closestPoint(new Point2D(5, 7)), new Point2D(5, 3));
    assertEquals(kdtree.closestPoint(new Point2D(5, 50)), new Point2D(10, 20));
    assertEquals(kdtree.closestPoint(new Point2D(0, 5)), new Point2D(-1, 10));
    assertEquals(kdtree.closestPoint(new Point2D(-5, -7)), new Point2D(-5, -10));
    assertEquals(kdtree.closestPoint(new Point2D(-75, -50)), new Point2D(-100, -100));
    assertEquals(kdtree.closestPoint(new Point2D(275, -5)), new Point2D(100, 100));
    assertEquals(kdtree.closestPoint(new Point2D(100, 100)), new Point2D(100, 100));
    assertEquals(kdtree.closestPoint(new Point2D(-100, -100)), new Point2D(-100, -100));
    assertEquals(kdtree.closestPoint(new Point2D(0, 0)), new Point2D(5, 3));
    assertEquals(kdtree.closestPoint(new Point2D(-3, 2)), new Point2D(5, 3));
  }

  @Test(expected = IllegalArgumentException.class)
  public void pointsWithinCircleWithNoPointInTree() {
    SetOfPoints kdtree = new PointKDTree(null);
    List<Point2D> pointsInCircle = kdtree.allPointsWithinCircle(new Point2D(0, 0), 5);
  }

  @Test
  public void pointsWithinCircleWithOnePointInTree() {
    ArrayList<Point2D> points = new ArrayList<>();
    points.add(new Point2D(2, 3));
    SetOfPoints kdtree = new PointKDTree(points);
    List<Point2D> pointsInCircle = kdtree.allPointsWithinCircle(new Point2D(0, 0), 5);
    assertEquals(pointsInCircle, points);
  }

  @Test
  public void pointsWithinCircleWithMultiplePointsInTreeCheckingByIncrementsofOne() {
    ArrayList<Point2D> points = new ArrayList<>();
    for (int i = -10; i <= 10; i++) {
      points.add(new Point2D(i, i));
    }
    SetOfPoints kdtree = new PointKDTree(points);
    List<Point2D> pointsInCircle1 = kdtree.allPointsWithinCircle(new Point2D(0, 0), 1);
    assertEquals(pointsInCircle1, new ArrayList<>(List.of(new Point2D(0, 0))));
    List<Point2D> pointsInCircle2 = kdtree.allPointsWithinCircle(new Point2D(0, 0), 2);
    assertTrue(new HashSet<>(pointsInCircle2).equals(
        new HashSet<>(new ArrayList<Point2D>(List.of(new Point2D(-1, -1),
            new Point2D(0, 0), new Point2D(1, 1))))));
    List<Point2D> pointsInCircle3 = kdtree.allPointsWithinCircle(new Point2D(0, 0), 3);
    assertTrue(new HashSet<>(pointsInCircle3).equals(
        new HashSet<>(new ArrayList<Point2D>(List.of(new Point2D(-2, -2), new Point2D(-1, -1),
            new Point2D(0, 0), new Point2D(1, 1), new Point2D(2, 2))))));
    List<Point2D> pointsInCircle4 = kdtree.allPointsWithinCircle(new Point2D(0, 0), 4);
    System.out.println(pointsInCircle4);
    assertTrue(new HashSet<>(pointsInCircle4).equals(
        new HashSet<>(new ArrayList<Point2D>(List.of(new Point2D(-2, -2), new Point2D(-1, -1),
            new Point2D(0, 0), new Point2D(1, 1), new Point2D(2, 2))))));
  }

  @Test
  public void pointsWithinCircleWithMultiplePointsInTreeCheckingByIncrementsAlongx() {
    ArrayList<Point2D> points = new ArrayList<>();
    for (int i = -10; i <= 10; i++) {
      points.add(new Point2D(i, 0));
    }
    SetOfPoints kdtree = new PointKDTree(points);
    List<Point2D> pointsInCircle1 = kdtree.allPointsWithinCircle(new Point2D(0, 0), 1);
    assertTrue(new HashSet<>(pointsInCircle1).equals(
        new HashSet<>(new ArrayList<Point2D>(List.of(new Point2D(-1, 0),
            new Point2D(0, 0), new Point2D(1, 0))))));
    List<Point2D> pointsInCircle2 = kdtree.allPointsWithinCircle(new Point2D(0, 0), 2);
    assertTrue(new HashSet<>(pointsInCircle2).equals(
        new HashSet<>(new ArrayList<Point2D>(List.of(new Point2D(-2, 0), new Point2D(-1, 0),
            new Point2D(0, 0), new Point2D(1, 0), new Point2D(2, 0))))));
    List<Point2D> pointsInCircle3 = kdtree.allPointsWithinCircle(new Point2D(0, 0), 3);
    assertTrue(new HashSet<>(pointsInCircle3).equals(
        new HashSet<>(new ArrayList<Point2D>(
            List.of(new Point2D(-3, 0), new Point2D(-2, 0), new Point2D(-1, 0),
                new Point2D(0, 0), new Point2D(1, 0), new Point2D(2, 0), new Point2D(3, 0))))));
    List<Point2D> pointsInCircle4 = kdtree.allPointsWithinCircle(new Point2D(0, 0), 4);
    assertTrue(new HashSet<>(pointsInCircle4).equals(
        new HashSet<>(new ArrayList<Point2D>(
            List.of(new Point2D(-4, 0), new Point2D(-3, 0), new Point2D(-2, 0), new Point2D(-1, 0),
                new Point2D(0, 0), new Point2D(1, 0), new Point2D(2, 0), new Point2D(3, 0),
                new Point2D(4, 0))))));
  }

  @Test
  public void pointsWithinCircleWithMultiplePointsInTreeCheckingByIncrementsAlongy() {
    ArrayList<Point2D> points = new ArrayList<>();
    for (int i = -10; i <= 10; i++) {
      points.add(new Point2D(0, i));
    }
    SetOfPoints kdtree = new PointKDTree(points);
    List<Point2D> pointsInCircle1 = kdtree.allPointsWithinCircle(new Point2D(0, 0), 1);
    assertTrue(new HashSet<>(pointsInCircle1).equals(
        new HashSet<>(new ArrayList<Point2D>(List.of(new Point2D(0, -1),
            new Point2D(0, 0), new Point2D(0, 1))))));
    List<Point2D> pointsInCircle2 = kdtree.allPointsWithinCircle(new Point2D(0, 0), 2);
    assertTrue(new HashSet<>(pointsInCircle2).equals(
        new HashSet<>(new ArrayList<Point2D>(List.of(new Point2D(0, -2), new Point2D(0, -1),
            new Point2D(0, 0), new Point2D(0, 1), new Point2D(0, 2))))));
    List<Point2D> pointsInCircle3 = kdtree.allPointsWithinCircle(new Point2D(0, 0), 3);
    assertTrue(new HashSet<>(pointsInCircle3).equals(
        new HashSet<>(new ArrayList<Point2D>(
            List.of(new Point2D(0, -3), new Point2D(0, -2), new Point2D(0, -1),
                new Point2D(0, 0), new Point2D(0, 1), new Point2D(0, 2), new Point2D(0, 3))))));
    List<Point2D> pointsInCircle4 = kdtree.allPointsWithinCircle(new Point2D(0, 0), 4);
    assertTrue(new HashSet<>(pointsInCircle4).equals(
        new HashSet<>(new ArrayList<Point2D>(
            List.of(new Point2D(0, -4), new Point2D(0, -3), new Point2D(0, -2), new Point2D(0, -1),
                new Point2D(0, 0), new Point2D(0, 1), new Point2D(0, 2), new Point2D(0, 3),
                new Point2D(0, 4))))));
  }

  @Test(expected = IllegalArgumentException.class)
  public void getPointsWithNoPointInTree() {
    SetOfPoints kdtree = new PointKDTree(null);
    List<Point2D> pointsInTree = kdtree.getPoints();
  }

  @Test
  public void getPointsWithOnePointInTree() {
    SetOfPoints kdtree = new PointKDTree(new ArrayList<>(List.of(new Point2D(1, 5))));
    List<Point2D> pointsInTree = kdtree.getPoints();
    assertEquals(pointsInTree, new ArrayList<>(List.of(new Point2D(1, 5))));
  }

  @Test
  public void getPointsWithManyPointInTree() {
    List<Point2D> pointList = new ArrayList<>();
    for (int i = -5; i <= 5; i++) {
      pointList.add(new Point2D(i, i));
    }
    SetOfPoints kdtree = new PointKDTree(pointList);
    List<Point2D> pointsInTree = kdtree.getPoints();
    assertTrue(new HashSet<>(pointsInTree).equals(new HashSet<>(new ArrayList<Point2D>(
        List.of(new Point2D(-5, -5), new Point2D(-4, -4), new Point2D(-3, -3), new Point2D(-2, -2),
            new Point2D(-1, -1), new Point2D(0, 0), new Point2D(1, 1), new Point2D(2, 2),
            new Point2D(3, 3), new Point2D(4, 4), new Point2D(5, 5))))));
  }

  @Test(expected = IllegalArgumentException.class)
  public void toStringWithNullPointInTree() {
    SetOfPoints kdtree = new PointKDTree(null);
    kdtree.toString();
  }

  @Test
  public void toStringWithNoPointInTree() {
    SetOfPoints kdtree = new PointKDTree(new ArrayList<>());
    assertEquals(kdtree.toString(), "This tree has no entry");
  }

}