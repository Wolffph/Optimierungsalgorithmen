import matplotlib as matplotlib
import matplotlib.pyplot as plt
import numpy as np
import pandas as pd
import seaborn as sns

data = pd.read_csv("/Users/pw-home/IdeaProjects/Optimierungsalgorithmen/data.csv", header=None, delimiter=";")
boxData = pd.read_csv("/Users/pw-home/IdeaProjects/Optimierungsalgorithmen/boxData.csv", header=None, delimiter=";")

xValues = np.fromstring(data.iloc[0].values.tolist()[0], dtype=int, sep=',')
yValues = np.fromstring(data.iloc[1].values.tolist()[0], dtype=int, sep=',')
boxXValues = np.fromstring(boxData.iloc[0].values.tolist()[0], dtype=int, sep=',')
boxYValues = np.fromstring(boxData.iloc[1].values.tolist()[0], dtype=int, sep=',')

# General plot configuration

box_linewidth = 0.3
rect_linewidth = 0.2
matplotlib.use('TkAgg')
fig = plt.figure()
plt.gca().set_aspect('equal', adjustable='box')
# plt.axis('off')
sns.set()
sns.set_style("ticks", {'axes.grid': False})
sns.despine(top=True, right=True, left=True, bottom=True)

# Plot the rectangles
for i in range(0, len(xValues), 4):
    plt.plot([xValues[i], xValues[i + 1]], [yValues[i], yValues[i]], 'b', linewidth=rect_linewidth)  # lower edges

    plt.plot([xValues[i + 2], xValues[i + 3]], [yValues[i + 2], yValues[i + 3]], 'b',
             linewidth=rect_linewidth)  # upper edges

    plt.plot([xValues[i], xValues[i]], [yValues[i], yValues[i + 2]], 'b', linewidth=rect_linewidth)  # left edges

    plt.plot([xValues[i + 1], xValues[i + 1]], [yValues[i], yValues[i + 2]], 'b',
             linewidth=rect_linewidth)  # right edges

# Plot the bounding boxes
for i in range(0, len(boxXValues), 4):
    plt.plot([boxXValues[i], boxXValues[i + 1]], [boxYValues[i], boxYValues[i]], 'orange',
             linewidth=box_linewidth)  # lower edges
    plt.plot([boxXValues[i + 2], boxXValues[i + 3]], [boxYValues[i + 2], boxYValues[i + 3]], 'orange',
             linewidth=box_linewidth)  # upper edges
    plt.plot([boxXValues[i], boxXValues[i]], [boxYValues[i], boxYValues[i + 2]], 'orange',
             linewidth=box_linewidth)  # left edges
    plt.plot([boxXValues[i + 1], boxXValues[i + 1]], [boxYValues[i], boxYValues[i + 2]], 'orange',
             linewidth=box_linewidth)  # right edges

# plt.show()

fig.savefig("/Users/pw-home/IdeaProjects/Optimierungsalgorithmen/graph.png", dpi=600)
fig.savefig('/Users/pw-home/IdeaProjects/Optimierungsalgorithmen/graph.eps', format='eps')
# fig.savefig('/Users/pw-home/IdeaProjects/Optimierungsalgorithmen/graph.svg', format = 'svg')
