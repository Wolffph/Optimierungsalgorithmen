import matplotlib.pyplot as plt
import numpy as np
import pandas as pd

data = pd.read_csv("/Users/pw-home/IdeaProjects/Optimierungsalgorithmen/data.csv", header=None, delimiter=";")
boxData = pd.read_csv("/Users/pw-home/IdeaProjects/Optimierungsalgorithmen/boxData.csv", header=None, delimiter=";")

xValues = np.fromstring(data.iloc[0].values.tolist()[0], dtype=int, sep=',')
yValues = np.fromstring(data.iloc[1].values.tolist()[0], dtype=int, sep=',')
boxXValues = np.fromstring(boxData.iloc[0].values.tolist()[0], dtype=int, sep=',')
boxYValues = np.fromstring(boxData.iloc[1].values.tolist()[0], dtype=int, sep=',')

fig = plt.figure()

# Plot the rectangles
for i in range(0, len(xValues), 4):
    plt.plot([xValues[i], xValues[i + 1]], [yValues[i], yValues[i]], 'b', linewidth=0.3)  # lower edges

    plt.plot([xValues[i + 2], xValues[i + 3]], [yValues[i + 2], yValues[i + 3]], 'b', linewidth=0.3)  # upper edges

    plt.plot([xValues[i], xValues[i]], [yValues[i], yValues[i + 2]], 'b', linewidth=0.3)  # left edges

    plt.plot([xValues[i + 1], xValues[i + 1]], [yValues[i], yValues[i + 2]], 'b', linewidth=0.3)  # right edges

# Plot the bounding boxes
for i in range(0, len(boxXValues), 4):
    plt.plot([boxXValues[i], boxXValues[i + 1]], [boxYValues[i], boxYValues[i]], 'orange', linewidth=0.3)  # lower edges
    plt.plot([boxXValues[i + 2], boxXValues[i + 3]], [boxYValues[i + 2], boxYValues[i + 3]], 'orange', linewidth=0.3)  # upper edges
    plt.plot([boxXValues[i], boxXValues[i]], [boxYValues[i], boxYValues[i + 2]], 'orange', linewidth=0.3)  # left edges
    plt.plot([boxXValues[i + 1], boxXValues[i + 1]], [boxYValues[i], boxYValues[i + 2]], 'orange', linewidth=0.3)  # right edges


# plt.show()

fig.savefig("/Users/pw-home/IdeaProjects/Optimierungsalgorithmen/graph.png")
