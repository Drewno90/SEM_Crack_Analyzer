import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Calculations {

	public Fracture[][] poprId(Fracture[][] siatka, int height, int width) {

		HashMap<Integer, Integer> temp = new HashMap<Integer, Integer>();

		for (int i = 0; i < height; i++)
			for (int j = 0; j < width; j++) {
				if (siatka[j][i].getId() != 0)
					temp.put(i * width + j, 1);
			}
		int flag = 0;
		do {
			flag = 0;
			for (int i = 0; i < height; i++)
				for (int j = 0; j < width; j++) {
					if (i > 0 && i < height - 1 && j > 0 && j < width - 1) {
						if (siatka[j][i].getId() != 0
								&& (siatka[j - 1][i].getId() != 0 || siatka[j - 1][i + 1].getId() != 0
										|| siatka[j - 1][i - 1].getId() != 0 || siatka[j][i - 1].getId() != 0
										|| siatka[j][i + 1].getId() != 0 || siatka[j + 1][i].getId() != 0
										|| siatka[j + 1][i + 1].getId() != 0 || siatka[j + 1][i - 1].getId() != 0)) {
							if (siatka[j][i].getId() > siatka[j - 1][i].getId() && siatka[j - 1][i].getId() != 0) {
								siatka[j][i].setId(siatka[j - 1][i].getId());
								flag = 1;
							} else if (siatka[j][i].getId() < siatka[j - 1][i].getId()
									&& siatka[j - 1][i].getId() != 0) {
								siatka[j - 1][i].setId(siatka[j][i].getId());
								flag = 1;
							}
							if (siatka[j][i].getId() > siatka[j - 1][i - 1].getId()
									&& siatka[j - 1][i - 1].getId() != 0) {
								siatka[j][i].setId(siatka[j - 1][i - 1].getId());
								flag = 1;
							} else if (siatka[j][i].getId() < siatka[j - 1][i - 1].getId()
									&& siatka[j - 1][i - 1].getId() != 0) {
								siatka[j - 1][i - 1].setId(siatka[j][i].getId());
								flag = 1;
							}
							if (siatka[j][i].getId() > siatka[j - 1][i + 1].getId()
									&& siatka[j - 1][i + 1].getId() != 0) {
								siatka[j][i].setId(siatka[j - 1][i + 1].getId());
								flag = 1;
							} else if (siatka[j][i].getId() < siatka[j - 1][i + 1].getId()
									&& siatka[j - 1][i + 1].getId() != 0) {
								siatka[j - 1][i + 1].setId(siatka[j][i].getId());
								flag = 1;
							}
							if (siatka[j][i].getId() > siatka[j][i + 1].getId() && siatka[j][i + 1].getId() != 0) {
								siatka[j][i].setId(siatka[j][i + 1].getId());
								flag = 1;
							} else if (siatka[j][i].getId() < siatka[j][i + 1].getId()
									&& siatka[j][i + 1].getId() != 0) {
								siatka[j][i + 1].setId(siatka[j][i].getId());
								flag = 1;
							}
							if (siatka[j][i].getId() > siatka[j][i - 1].getId() && siatka[j][i - 1].getId() != 0) {
								siatka[j][i].setId(siatka[j][i - 1].getId());
								flag = 1;
							} else if (siatka[j][i].getId() < siatka[j][i - 1].getId()
									&& siatka[j][i - 1].getId() != 0) {
								siatka[j][i - 1].setId(siatka[j][i].getId());
								flag = 1;
							}
							if (siatka[j][i].getId() > siatka[j + 1][i].getId() && siatka[j + 1][i].getId() != 0) {
								siatka[j][i].setId(siatka[j + 1][i].getId());
								flag = 1;
							} else if (siatka[j][i].getId() < siatka[j + 1][i].getId()
									&& siatka[j + 1][i].getId() != 0) {
								siatka[j + 1][i].setId(siatka[j][i].getId());
								flag = 1;
							}
							if (siatka[j][i].getId() > siatka[j + 1][i - 1].getId()
									&& siatka[j + 1][i - 1].getId() != 0) {
								siatka[j][i].setId(siatka[j + 1][i - 1].getId());
								flag = 1;
							} else if (siatka[j][i].getId() < siatka[j + 1][i - 1].getId()
									&& siatka[j + 1][i - 1].getId() != 0) {
								siatka[j + 1][i - 1].setId(siatka[j][i].getId());
								flag = 1;
							}
							if (siatka[j][i].getId() > siatka[j + 1][i + 1].getId()
									&& siatka[j + 1][i + 1].getId() != 0) {
								siatka[j][i].setId(siatka[j + 1][i + 1].getId());
								flag = 1;
							} else if (siatka[j][i].getId() < siatka[j + 1][i + 1].getId()
									&& siatka[j + 1][i + 1].getId() != 0) {
								siatka[j + 1][i + 1].setId(siatka[j][i].getId());
								flag = 1;
							}

						}
					} else if (i == 0 && j > 0 && j < width - 1) {
						if (siatka[j][i].getId() != 0 && (siatka[j - 1][i].getId() != 0
								|| siatka[j - 1][i + 1].getId() != 0 || siatka[j][i + 1].getId() != 0
								|| siatka[j + 1][i].getId() != 0 || siatka[j + 1][i + 1].getId() != 0)) {
							if (siatka[j][i].getId() > siatka[j - 1][i].getId() && siatka[j - 1][i].getId() != 0) {
								siatka[j][i].setId(siatka[j - 1][i].getId());
								flag = 1;
							} else if (siatka[j][i].getId() < siatka[j - 1][i].getId()
									&& siatka[j - 1][i].getId() != 0) {
								siatka[j - 1][i].setId(siatka[j][i].getId());
								flag = 1;
							}
							if (siatka[j][i].getId() > siatka[j - 1][i + 1].getId()
									&& siatka[j - 1][i + 1].getId() != 0) {
								siatka[j][i].setId(siatka[j - 1][i + 1].getId());
								flag = 1;
							} else if (siatka[j][i].getId() < siatka[j - 1][i + 1].getId()
									&& siatka[j - 1][i + 1].getId() != 0) {
								siatka[j - 1][i + 1].setId(siatka[j][i].getId());
								flag = 1;
							}
							if (siatka[j][i].getId() > siatka[j][i + 1].getId() && siatka[j][i + 1].getId() != 0) {
								siatka[j][i].setId(siatka[j][i + 1].getId());
								flag = 1;
							} else if (siatka[j][i].getId() < siatka[j][i + 1].getId()
									&& siatka[j][i + 1].getId() != 0) {
								siatka[j][i + 1].setId(siatka[j][i].getId());
								flag = 1;
							}
							if (siatka[j][i].getId() > siatka[j + 1][i].getId() && siatka[j + 1][i].getId() != 0) {
								siatka[j][i].setId(siatka[j + 1][i].getId());
								flag = 1;
							} else if (siatka[j][i].getId() < siatka[j + 1][i].getId()
									&& siatka[j + 1][i].getId() != 0) {
								siatka[j + 1][i].setId(siatka[j][i].getId());
								flag = 1;
							}
							if (siatka[j][i].getId() > siatka[j + 1][i + 1].getId()
									&& siatka[j + 1][i + 1].getId() != 0) {
								siatka[j][i].setId(siatka[j + 1][i + 1].getId());
								flag = 1;
							} else if (siatka[j][i].getId() < siatka[j + 1][i + 1].getId()
									&& siatka[j + 1][i + 1].getId() != 0) {
								siatka[j + 1][i + 1].setId(siatka[j][i].getId());
								flag = 1;
							}
						}
					} else if (i == height && j > 0 && j < width - 1) {
						if (siatka[j][i].getId() != 0 && (siatka[j - 1][i].getId() != 0
								|| siatka[j - 1][i - 1].getId() != 0 || siatka[j][i - 1].getId() != 0
								|| siatka[j + 1][i].getId() != 0 || siatka[j + 1][i - 1].getId() != 0)) {
							if (siatka[j][i].getId() > siatka[j - 1][i].getId() && siatka[j - 1][i].getId() != 0) {
								siatka[j][i].setId(siatka[j - 1][i].getId());
								flag = 1;
							} else if (siatka[j][i].getId() < siatka[j - 1][i].getId()
									&& siatka[j - 1][i].getId() != 0) {
								siatka[j - 1][i].setId(siatka[j][i].getId());
								flag = 1;
							}
							if (siatka[j][i].getId() > siatka[j - 1][i - 1].getId()
									&& siatka[j - 1][i - 1].getId() != 0) {
								siatka[j][i].setId(siatka[j - 1][i - 1].getId());
								flag = 1;
							} else if (siatka[j][i].getId() < siatka[j - 1][i - 1].getId()
									&& siatka[j - 1][i - 1].getId() != 0) {
								siatka[j - 1][i - 1].setId(siatka[j][i].getId());
								flag = 1;
							}
							if (siatka[j][i].getId() > siatka[j][i - 1].getId() && siatka[j][i - 1].getId() != 0) {
								siatka[j][i].setId(siatka[j][i - 1].getId());
								flag = 1;
							} else if (siatka[j][i].getId() < siatka[j][i - 1].getId()
									&& siatka[j][i - 1].getId() != 0) {
								siatka[j][i - 1].setId(siatka[j][i].getId());
								flag = 1;
							}
							if (siatka[j][i].getId() > siatka[j + 1][i].getId() && siatka[j + 1][i].getId() != 0) {
								siatka[j][i].setId(siatka[j + 1][i].getId());
								flag = 1;
							} else if (siatka[j][i].getId() < siatka[j + 1][i].getId()
									&& siatka[j + 1][i].getId() != 0) {
								siatka[j + 1][i].setId(siatka[j][i].getId());
								flag = 1;
							}
							if (siatka[j][i].getId() > siatka[j + 1][i - 1].getId()
									&& siatka[j + 1][i - 1].getId() != 0) {
								siatka[j][i].setId(siatka[j + 1][i - 1].getId());
								flag = 1;
							} else if (siatka[j][i].getId() < siatka[j + 1][i - 1].getId()
									&& siatka[j + 1][i - 1].getId() != 0) {
								siatka[j + 1][i - 1].setId(siatka[j][i].getId());
								flag = 1;
							}
						}

					} else if (i > 0 && i < height - 1 && j == 0) {
						if (siatka[j][i].getId() != 0 && (siatka[j][i - 1].getId() != 0 || siatka[j][i + 1].getId() != 0
								|| siatka[j + 1][i].getId() != 0 || siatka[j + 1][i + 1].getId() != 0
								|| siatka[j + 1][i - 1].getId() != 0)) {
							if (siatka[j][i].getId() > siatka[j][i + 1].getId() && siatka[j][i + 1].getId() != 0) {
								siatka[j][i].setId(siatka[j][i + 1].getId());
								flag = 1;
							} else if (siatka[j][i].getId() < siatka[j][i + 1].getId()
									&& siatka[j][i + 1].getId() != 0) {
								siatka[j][i + 1].setId(siatka[j][i].getId());
								flag = 1;
							}
							if (siatka[j][i].getId() > siatka[j][i - 1].getId() && siatka[j][i - 1].getId() != 0) {
								siatka[j][i].setId(siatka[j][i - 1].getId());
								flag = 1;
							} else if (siatka[j][i].getId() < siatka[j][i - 1].getId()
									&& siatka[j][i - 1].getId() != 0) {
								siatka[j][i - 1].setId(siatka[j][i].getId());
								flag = 1;
							}
							if (siatka[j][i].getId() > siatka[j + 1][i].getId() && siatka[j + 1][i].getId() != 0) {
								siatka[j][i].setId(siatka[j + 1][i].getId());
								flag = 1;
							} else if (siatka[j][i].getId() < siatka[j + 1][i].getId()
									&& siatka[j + 1][i].getId() != 0) {
								siatka[j + 1][i].setId(siatka[j][i].getId());
								flag = 1;
							}
							if (siatka[j][i].getId() > siatka[j + 1][i - 1].getId()
									&& siatka[j + 1][i - 1].getId() != 0) {
								siatka[j][i].setId(siatka[j + 1][i - 1].getId());
								flag = 1;
							} else if (siatka[j][i].getId() < siatka[j + 1][i - 1].getId()
									&& siatka[j + 1][i - 1].getId() != 0) {
								siatka[j + 1][i - 1].setId(siatka[j][i].getId());
								flag = 1;
							}
							if (siatka[j][i].getId() > siatka[j + 1][i + 1].getId()
									&& siatka[j + 1][i + 1].getId() != 0) {
								siatka[j][i].setId(siatka[j + 1][i + 1].getId());
								flag = 1;
							} else if (siatka[j][i].getId() < siatka[j + 1][i + 1].getId()
									&& siatka[j + 1][i + 1].getId() != 0) {
								siatka[j + 1][i + 1].setId(siatka[j][i].getId());
								flag = 1;
							}

						}
					} else if (i > 0 && i < height - 1 && j == width - 1) {
						if (siatka[j][i].getId() != 0 && (siatka[j - 1][i].getId() != 0
								|| siatka[j - 1][i + 1].getId() != 0 || siatka[j - 1][i - 1].getId() != 0
								|| siatka[j][i - 1].getId() != 0 || siatka[j][i + 1].getId() != 0)) {
							if (siatka[j][i].getId() > siatka[j - 1][i].getId() && siatka[j - 1][i].getId() != 0) {
								siatka[j][i].setId(siatka[j - 1][i].getId());
								flag = 1;
							} else if (siatka[j][i].getId() < siatka[j - 1][i].getId()
									&& siatka[j - 1][i].getId() != 0) {
								siatka[j - 1][i].setId(siatka[j][i].getId());
								flag = 1;
							}
							if (siatka[j][i].getId() > siatka[j - 1][i - 1].getId()
									&& siatka[j - 1][i - 1].getId() != 0) {
								siatka[j][i].setId(siatka[j - 1][i - 1].getId());
								flag = 1;
							} else if (siatka[j][i].getId() < siatka[j - 1][i - 1].getId()
									&& siatka[j - 1][i - 1].getId() != 0) {
								siatka[j - 1][i - 1].setId(siatka[j][i].getId());
								flag = 1;
							}
							if (siatka[j][i].getId() > siatka[j - 1][i + 1].getId()
									&& siatka[j - 1][i + 1].getId() != 0) {
								siatka[j][i].setId(siatka[j - 1][i + 1].getId());
								flag = 1;
							} else if (siatka[j][i].getId() < siatka[j - 1][i + 1].getId()
									&& siatka[j - 1][i + 1].getId() != 0) {
								siatka[j - 1][i + 1].setId(siatka[j][i].getId());
								flag = 1;
							}
							if (siatka[j][i].getId() > siatka[j][i + 1].getId() && siatka[j][i + 1].getId() != 0) {
								siatka[j][i].setId(siatka[j][i + 1].getId());
								flag = 1;
							} else if (siatka[j][i].getId() < siatka[j][i + 1].getId()
									&& siatka[j][i + 1].getId() != 0) {
								siatka[j][i + 1].setId(siatka[j][i].getId());
								flag = 1;
							}
							if (siatka[j][i].getId() > siatka[j][i - 1].getId() && siatka[j][i - 1].getId() != 0) {
								siatka[j][i].setId(siatka[j][i - 1].getId());
								flag = 1;
							} else if (siatka[j][i].getId() < siatka[j][i - 1].getId()
									&& siatka[j][i - 1].getId() != 0) {
								siatka[j][i - 1].setId(siatka[j][i].getId());
								flag = 1;
							}
						}
					} else if (i == 0 && j == 0) {
						if (siatka[j][i].getId() != 0 && (siatka[j][i + 1].getId() != 0 || siatka[j + 1][i].getId() != 0
								|| siatka[j + 1][i + 1].getId() != 0)) {
							if (siatka[j][i].getId() > siatka[j][i + 1].getId() && siatka[j][i + 1].getId() != 0) {
								siatka[j][i].setId(siatka[j][i + 1].getId());
								flag = 1;
							} else if (siatka[j][i].getId() < siatka[j][i + 1].getId()
									&& siatka[j][i + 1].getId() != 0) {
								siatka[j][i + 1].setId(siatka[j][i].getId());
								flag = 1;
							}
							if (siatka[j][i].getId() > siatka[j + 1][i].getId() && siatka[j + 1][i].getId() != 0) {
								siatka[j][i].setId(siatka[j + 1][i].getId());
								flag = 1;
							} else if (siatka[j][i].getId() < siatka[j + 1][i].getId()
									&& siatka[j + 1][i].getId() != 0) {
								siatka[j + 1][i].setId(siatka[j][i].getId());
								flag = 1;
							}
							if (siatka[j][i].getId() > siatka[j + 1][i + 1].getId()
									&& siatka[j + 1][i + 1].getId() != 0) {
								siatka[j][i].setId(siatka[j + 1][i + 1].getId());
								flag = 1;
							} else if (siatka[j][i].getId() < siatka[j + 1][i + 1].getId()
									&& siatka[j + 1][i + 1].getId() != 0) {
								siatka[j + 1][i + 1].setId(siatka[j][i].getId());
								flag = 1;
							}
						}
					} else if (i == 0 && j == width - 1) {
						if (siatka[j][i].getId() != 0 && (siatka[j - 1][i].getId() != 0
								|| siatka[j - 1][i + 1].getId() != 0 || siatka[j][i + 1].getId() != 0)) {
							if (siatka[j][i].getId() > siatka[j - 1][i].getId() && siatka[j - 1][i].getId() != 0) {
								siatka[j][i].setId(siatka[j - 1][i].getId());
								flag = 1;
							} else if (siatka[j][i].getId() < siatka[j - 1][i].getId()
									&& siatka[j - 1][i].getId() != 0) {
								siatka[j - 1][i].setId(siatka[j][i].getId());
								flag = 1;
							}
							if (siatka[j][i].getId() > siatka[j - 1][i + 1].getId()
									&& siatka[j - 1][i + 1].getId() != 0) {
								siatka[j][i].setId(siatka[j - 1][i + 1].getId());
								flag = 1;
							} else if (siatka[j][i].getId() < siatka[j - 1][i + 1].getId()
									&& siatka[j - 1][i + 1].getId() != 0) {
								siatka[j - 1][i + 1].setId(siatka[j][i].getId());
								flag = 1;
							}
							if (siatka[j][i].getId() > siatka[j][i + 1].getId() && siatka[j][i + 1].getId() != 0) {
								siatka[j][i].setId(siatka[j][i + 1].getId());
								flag = 1;
							} else if (siatka[j][i].getId() < siatka[j][i + 1].getId()
									&& siatka[j][i + 1].getId() != 0) {
								siatka[j][i + 1].setId(siatka[j][i].getId());
								flag = 1;
							}

						}
					} else if (i == height - 1 && j == 0) {
						if (siatka[j][i].getId() != 0 && (siatka[j][i - 1].getId() != 0 || siatka[j + 1][i].getId() != 0
								|| siatka[j + 1][i - 1].getId() != 0)) {
							if (siatka[j][i].getId() > siatka[j][i - 1].getId() && siatka[j][i - 1].getId() != 0) {
								siatka[j][i].setId(siatka[j][i - 1].getId());
								flag = 1;
							} else if (siatka[j][i].getId() < siatka[j][i - 1].getId()
									&& siatka[j][i - 1].getId() != 0) {
								siatka[j][i - 1].setId(siatka[j][i].getId());
								flag = 1;
							}
							if (siatka[j][i].getId() > siatka[j + 1][i].getId() && siatka[j + 1][i].getId() != 0) {
								siatka[j][i].setId(siatka[j + 1][i].getId());
								flag = 1;
							} else if (siatka[j][i].getId() < siatka[j + 1][i].getId()
									&& siatka[j + 1][i].getId() != 0) {
								siatka[j + 1][i].setId(siatka[j][i].getId());
								flag = 1;
							}
							if (siatka[j][i].getId() > siatka[j + 1][i - 1].getId()
									&& siatka[j + 1][i - 1].getId() != 0) {
								siatka[j][i].setId(siatka[j + 1][i - 1].getId());
								flag = 1;
							} else if (siatka[j][i].getId() < siatka[j + 1][i - 1].getId()
									&& siatka[j + 1][i - 1].getId() != 0) {
								siatka[j + 1][i - 1].setId(siatka[j][i].getId());
								flag = 1;
							}

						}
					} else if (i == height - 1 && j == width - 1) {
						if (siatka[j][i].getId() != 0 && (siatka[j - 1][i].getId() != 0
								|| siatka[j - 1][i - 1].getId() != 0 || siatka[j][i - 1].getId() != 0)) {
							if (siatka[j][i].getId() > siatka[j - 1][i].getId() && siatka[j - 1][i].getId() != 0) {
								siatka[j][i].setId(siatka[j - 1][i].getId());
								flag = 1;
							} else if (siatka[j][i].getId() < siatka[j - 1][i].getId()
									&& siatka[j - 1][i].getId() != 0) {
								siatka[j - 1][i].setId(siatka[j][i].getId());
								flag = 1;
							}
							if (siatka[j][i].getId() > siatka[j - 1][i - 1].getId()
									&& siatka[j - 1][i - 1].getId() != 0) {
								siatka[j][i].setId(siatka[j - 1][i - 1].getId());
								flag = 1;
							} else if (siatka[j][i].getId() < siatka[j - 1][i - 1].getId()
									&& siatka[j - 1][i - 1].getId() != 0) {
								siatka[j - 1][i - 1].setId(siatka[j][i].getId());
								flag = 1;
							}
							if (siatka[j][i].getId() > siatka[j][i - 1].getId() && siatka[j][i - 1].getId() != 0) {
								siatka[j][i].setId(siatka[j][i - 1].getId());
								flag = 1;
							} else if (siatka[j][i].getId() < siatka[j][i - 1].getId()
									&& siatka[j][i - 1].getId() != 0) {
								siatka[j][i - 1].setId(siatka[j][i].getId());
								flag = 1;
							}

						}
					}
				}
		} while (flag == 1);

		return siatka;

	}

	public int WorkBorder(int[][] a, int height, int width) {
		int j = 0, k = 0;
		System.out.println(height);
		for (int i = 0; i < height; i++) {
			while (j < width && (a[i][j] == 0 || a[i][j] >= 251)) {
				j++;
			}
			if (j == width) {
				k = i;
				return k;
			}
			j = 0;
		}
		return k;
	}

	public int oblicz_prog(int height, int width, int[][] pixels) {
		int prog, licznik = 0, mianownik = 0, Gx, Gy, G;

		for (int y = 1; y < height - 1; y++)
			for (int x = 1; x < width - 1; x++) {
				Gx = Math.abs(pixels[y][x + 1] - pixels[y][x - 1]);
				Gy = Math.abs(pixels[y + 1][x] - pixels[y - 1][x]);
				G = Math.max(Gx, Gy);
				licznik = licznik + pixels[y][x] * G;
				mianownik = mianownik + G;
			}
		prog = licznik / mianownik;
		System.out.println("Próg: " + prog);
		return prog;
	}

	public int Otsu(int height, int width, int[][] pixels) {
		// Calculate histogram
		int[] histData = new int[256];
		for (int i = 0; i < height; i++)
			for (int j = 0; j < width; j++) {
				int h = pixels[i][j];
				histData[h]++;
				System.out.println(h);
			}

		// Total number of pixels
		int total = width * height;

		float sum = 0;
		for (int t = 0; t < 256; t++)
			sum += t * histData[t];

		float sumB = 0;
		int wB = 0;
		int wF = 0;

		float varMax = 0;
		int threshold = 0;

		for (int t = 0; t < 256; t++) {
			wB += histData[t]; // Weight Background
			if (wB == 0)
				continue;

			wF = total - wB; // Weight Foreground
			if (wF == 0)
				break;

			sumB += (float) (t * histData[t]);

			float mB = sumB / wB; // Mean Background
			float mF = (sum - sumB) / wF; // Mean Foreground

			// Calculate Between Class Variance
			float varBetween = (float) wB * (float) wF * (mB - mF) * (mB - mF);

			// Check if new maximum found
			if (varBetween > varMax) {
				varMax = varBetween;
				threshold = t;
			}
		}
		System.out.println(threshold);
		return threshold;
	}

	public double calcAngle(int x11, int x22, int y11, int y22) {
		return Math.round(Math.atan2(y22 - y11, x22 - x11) * 180 / Math.PI);
	}

	public double calcCrackLength(int x1, int x2, int y1, int y2) {
		return Math.sqrt(((x2 - x1) * (x2 - x1) + (y1 - y2) * (y1 - y2)));
	}

	public double calcLengthtoAreaRatio(double length, double area) {
		return length / area;
	}

	public int[][] getPixelsColor(int height, int width, int[][] pixels, BufferedImage targetImg) {
		for (int i = 0; i < height; i++)
			for (int j = 0; j < width; j++) {
				Color mycolor = new Color(targetImg.getRGB(j, i));
				int r = mycolor.getRed();
				int b = mycolor.getBlue();
				int g = mycolor.getGreen();

				pixels[i][j] = ((int) (r + g + b)) / 3;

			}
		return pixels;
	}

	public BufferedImage setPixelsColor(int height, int width, Fracture[][] siatka, BufferedImage targetImg) {
		int[] a = new int[3];
		WritableRaster wr = targetImg.getRaster();
		for (int i = 0; i < height; i++)
			for (int j = 0; j < width; j++) {
				
				if(siatka[j][i].getColor().equals(Color.yellow))
				{
					a[0] = 254; a[1] = 254; a[2] = 54;
				}else
				{
					a[0] = siatka[j][i].getColor().getRed(); a[1] = siatka[j][i].getColor().getGreen(); a[2] = siatka[j][i].getColor().getBlue();
				}
						
				wr.setPixel(j, i, a);

			}
		return targetImg;
	}
	
	public Fracture[][] doBinaryzation(int height, int width, int[][] pixels, int shadow, Fracture[][] siatka,
			int workGround) {
		int num = 0;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (i >= workGround) {
					siatka[j][i] = new Fracture(j, i, 0, 0, false, new Color(pixels[i][j],pixels[i][j],pixels[i][j]) , false);
				} else if ((pixels[i][j] <= shadow) && i < workGround) {
					siatka[j][i] = new Fracture(j, i, 0, 0, false, Color.black, false);
				} else if (i < workGround && pixels[i][j] > shadow) {

					if (j > 0 && i > 0 && j < width - 1 && i < height - 1) {
						if (pixels[i - 1][j - 1] <= shadow && pixels[i - 1][j] <= shadow
								&& pixels[i - 1][j + 1] <= shadow && pixels[i][j - 1] <= shadow
								&& pixels[i][j + 1] <= shadow && pixels[i + 1][j - 1] <= shadow
								&& pixels[i + 1][j] <= shadow && pixels[i + 1][j + 1] <= shadow) {
							siatka[j][i] = new Fracture(j, i, 0, 0, false, Color.black, false);
						} else if (siatka[j - 1][i - 1].getId() == 0 && siatka[j][i - 1].getId() == 0
								&& siatka[j + 1][i - 1].getId() == 0 && siatka[j - 1][i].getId() == 0) {
							num++;
							siatka[j][i] = new Fracture(j, i, num, num, false, Color.yellow, false);
						} else if (siatka[j - 1][i - 1].getId() != 0)
							siatka[j][i] = new Fracture(j, i, num, siatka[j - 1][i - 1].getId(), false, Color.yellow,
									false);
						else if (siatka[j][i - 1].getId() != 0)
							siatka[j][i] = new Fracture(j, i, num, siatka[j][i - 1].getId(), false, Color.yellow,
									false);
						else if (siatka[j + 1][i - 1].getId() != 0)
							siatka[j][i] = new Fracture(j, i, num, siatka[j + 1][i - 1].getId(), false, Color.yellow,
									false);
						else if (siatka[j - 1][i].getId() != 0)
							siatka[j][i] = new Fracture(j, i, num, siatka[j - 1][i].getId(), false, Color.yellow,
									false);

					} else if (j == 0 && i > 0) {

						if (siatka[j][i - 1].getId() == 0 && siatka[j + 1][i - 1].getId() == 0) {
							num++;
							siatka[j][i] = new Fracture(j, i, num, num, false, Color.yellow, false);
						} else if (siatka[j][i - 1].getId() != 0)
							siatka[j][i] = new Fracture(j, i, num, siatka[j][i - 1].getId(), false, Color.yellow,
									false);
						else if (siatka[j + 1][i - 1].getId() != 0)
							siatka[j][i] = new Fracture(j, i, num, siatka[j + 1][i - 1].getId(), false, Color.yellow,
									false);
						else {
							siatka[j][i] = new Fracture(j, i, 0, 0, false, Color.black, false);
						}
					} else if (i == 0 && j > 0) {
						if (siatka[j - 1][i].getId() == 0) {
							num++;
							siatka[j][i] = new Fracture(j, i, num, num, false, Color.yellow, false);
						} else if (siatka[j - 1][i].getId() != 0)
							siatka[j][i] = new Fracture(j, i, num, siatka[j - 1][i].getId(), false, Color.yellow,
									false);
						else {
							siatka[j][i] = new Fracture(j, i, 0, 0, false, Color.black, false);
						}

					} else if (i == 0 && j == 0) {
						num++;
						siatka[j][i] = new Fracture(j, i, num, num, false, Color.yellow, false);
					} else {
						siatka[j][i] = new Fracture(j, i, 0, 0, false, Color.black, false);
					}

				} else {
					siatka[j][i] = new Fracture(j, i, 0, 0, false, Color.black, false);
				}
			}
		}
		return siatka;
	}

	public Fracture[][] eliminateSmallCracks(int height, int width, Fracture[][] siatka, double minCrackLength,
			double lengthToAreaRatio, Set<Integer> crackSet) {

		int x1, x2, y1, y2;
		Integer help[] = new Integer[crackSet.size()];
		HashMap<Integer, Integer> mapa = new HashMap<Integer, Integer>();
		HashMap<Integer, ArrayList<Fracture>> fracList = new HashMap<Integer, ArrayList<Fracture>>();
		crackSet.toArray(help);

		for (int i = 0; i < help.length; i++)
			mapa.put(help[i] , i);

		for (int i = 0; i < height; i++)
			for (int j = 0; j < width; j++) {
				if (siatka[j][i].getId() != 0) {
					int k = mapa.get(siatka[j][i].getId());
					if(!fracList.containsKey(k))
						fracList.put(k, new ArrayList<Fracture>());
					fracList.get(k).add(siatka[j][i]);
				}
			}
		double rozmiar = 0;
		double maxRoz = 0;
		for (int i = 0; i < crackSet.size(); i++) {
			maxRoz = 0;
			for (int z = 0; z < fracList.get(i).size(); z++)
				for (int j = 0; j < fracList.get(i).size(); j++) {
					x1 = fracList.get(i).get(z).getX();
					y1 = fracList.get(i).get(z).getY();
					x2 = fracList.get(i).get(j).getX();
					y2 = fracList.get(i).get(j).getY();
					rozmiar = calcCrackLength(x1, x2, y2, y1); 
					
					if (rozmiar > maxRoz) {
						maxRoz = rozmiar;
					}
				}
			if (maxRoz / fracList.get(i).size() > lengthToAreaRatio && maxRoz < minCrackLength)
				for (int j = 0; j < fracList.get(i).size(); j++) {
					Fracture k = fracList.get(i).get(j);
					siatka[k.getX()][k.getY()] = new Fracture(k.getX(), k.getY(), 0, 0, false, Color.black, false);
				}
		}
		return siatka;
	}

	public Fracture[][] expandCracks(Fracture[][] siatka, int height, int width, int workGround) {
		Fracture f;
		for (int i = 0; i < height; i++)
			for (int j = 0; j < width; j++)
				if (j > 0 && i > 0 && j < width - 1 && i < height - 1) { 
					if(i<workGround){
					if (siatka[j][i - 1].getId() != 0 || siatka[j - 1][i].getId() != 0
							|| siatka[j - 1][i - 1].getId() != 0 || siatka[j + 1][i - 1].getId() != 0
							|| siatka[j][i + 1].getId() != 0 || siatka[j + 1][i].getId() != 0
							|| siatka[j - 1][i + 1].getId() != 0 || siatka[j + 1][i + 1].getId() != 0) {
						if (siatka[j][i - 1].getId() != 0 && siatka[j][i - 1].isExpand() == false) {
							f = new Fracture(j, i, siatka[j][i - 1].getNumber(), siatka[j][i - 1].getId(), false,
									Color.yellow, true);
							siatka[j][i] = f;
						} else if (siatka[j - 1][i].getId() != 0 && siatka[j - 1][i].isExpand() == false) {
							f = new Fracture(j, i, siatka[j - 1][i].getNumber(), siatka[j - 1][i].getId(), false,
									Color.yellow, true);
							siatka[j][i] = f;
						} else if (siatka[j][i + 1].getId() != 0 && siatka[j][i + 1].isExpand() == false) {
							f = new Fracture(j, i, siatka[j][i + 1].getNumber(), siatka[j][i + 1].getId(), false,
									Color.yellow, true);
							siatka[j][i] = f;
						} else if (siatka[j + 1][i].getId() != 0 && siatka[j + 1][i].isExpand() == false) {
							f = new Fracture(j, i, siatka[j + 1][i].getNumber(), siatka[j + 1][i].getId(), false,
									Color.yellow, true);
							siatka[j][i] = f;
						} else if (siatka[j - 1][i - 1].getId() != 0 && siatka[j - 1][i - 1].isExpand() == false) {
							f = new Fracture(j, i, siatka[j - 1][i - 1].getNumber(), siatka[j - 1][i - 1].getId(),
									false, Color.yellow, true);
							siatka[j][i] = f;
						} else if (siatka[j - 1][i + 1].getId() != 0 && siatka[j - 1][i + 1].isExpand() == false) {
							f = new Fracture(j, i, siatka[j - 1][i + 1].getNumber(), siatka[j - 1][i + 1].getId(),
									false, Color.yellow, true);
							siatka[j][i] = f;
						} else if (siatka[j + 1][i + 1].getId() != 0 && siatka[j + 1][i + 1].isExpand() == false) {
							f = new Fracture(j, i, siatka[j + 1][i + 1].getNumber(), siatka[j + 1][i + 1].getId(),
									false, Color.yellow, true);
							siatka[j][i] = f;
						} else if (siatka[j + 1][i - 1].getId() != 0 && siatka[j + 1][i - 1].isExpand() == false) {
							f = new Fracture(j, i, siatka[j + 1][i - 1].getNumber(), siatka[j + 1][i - 1].getId(),
									false, Color.yellow, true);
							siatka[j][i] = f;
						} else {
							f = new Fracture(j, i, 0, 0, false, Color.black, false);
							siatka[j][i] = f;
						}

					} else {
						f = new Fracture(j, i, 0, 0, false, Color.black, false);
						siatka[j][i] = f;
					}
					}
				} else {
					f = new Fracture(j, i, 0, 0, false, new Color(siatka[j][i].getColor().getRGB()), false);
					siatka[j][i] = f;
				}
		return siatka;
	}
	

}
