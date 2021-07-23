import matplotlib.pyplot as plt
import numpy as np
import pandas as pd

data = pd.read_csv("/Users/pw-home/IdeaProjects/Optimierungsalgorithmen/data.csv", header=None, delimiter=";")

xValues = np.fromstring(data.iloc[0].values.tolist()[0], dtype=int, sep=',')
yValues = np.fromstring(data.iloc[1].values.tolist()[0], dtype=int, sep=',')

fig = plt.figure()

for i in range(0, len(xValues), 4):
    plt.plot([xValues[i], xValues[i + 1]], [yValues[i], yValues[i]], 'b', linewidth=0.3)  # lower edges

    plt.plot([xValues[i + 2], xValues[i + 3]], [yValues[i + 2], yValues[i + 3]], 'b', linewidth=0.3)  # upper edges

    plt.plot([xValues[i], xValues[i]], [yValues[i], yValues[i + 2]], 'b', linewidth=0.3)  # left edges

    plt.plot([xValues[i + 1], xValues[i + 1]], [yValues[i], yValues[i + 2]], 'b', linewidth=0.3)  # right edges
# plt.show()

fig.savefig("/Users/pw-home/IdeaProjects/Optimierungsalgorithmen/graph.png")
