### Consistency increaser 

### Table of Contents

1. [Algorithm](#algorithm)
2. [Example of data](#example-of-data)
3. [Example of a result](#example-of-a-result)
4. [Summary testing diagrams](#summary-testing-diagrams)
   1. [part 1](#part-1)
   2. [part 2-1](#part-2-1)
   3. [part 2-2](#part-2-2)
5. [Commercial potential of the work](#commercial-potential-of-the-work)

In simple terms, this project is designed to process large volumes of strictly ordered data represented as binary matrices at high speed. It assists in handling datasets and achieving a consistent order of alternatives. Areas where my project can be beneficial include:
- Astronomical research in big data analysis.
- Meteorological research.
- Banking or other systems with extensive unstructured numerical tables, and so on.

The research I have utilized:
1. DOI: 10.1504/IJMDM.2017.10002944
2. DOI: 10.1504/IJMDM.2020.10029719
3. DOI: 10.1016/S0377-2217(02)00227-8
4. DOI:10.1016/S0305-0548(03)00022-4 (...)

#### Algoritm 

<p align="center">
<img width="800" alt="algo" src="https://github.com/mykhailo-arkhipov/mykhailo-arkhipov/assets/122175623/0e74c0d3-5f91-44f4-ba9b-9f76b1473799">
</p>

#### Example of data

The program accepts binary matrices of pairwise comparisons. Initially, these matrices can be presented in the form of tables with any content. Data tables need to be prepared in advance to work with the program (often individually automated for each specific task), and then the process of enhancing consistency can be initialized.

<p align="center">
<img width="656" alt="example_table" src="https://github.com/mykhailo-arkhipov/Consistency_Increaser/assets/122175623/f27b9660-a50a-4569-a308-73fb638faf0c">
</p>

#### Example of a result 

As a visual example, you can observe a graph that illustrates a consistent pairwise comparison matrix (or a matrix with improved consistency).

<p align="center">
<img width="800" alt="graph" src="https://github.com/mykhailo-arkhipov/mykhailo-arkhipov/assets/122175623/a896e168-72a1-4a3c-929b-950a1c285dd3">
</p>

#### Summary testing diagrams

Research tests were conducted to find dependencies between the number of cycles and the number of alternatives (1), the number of alternatives and the time for the initial cycle detection (1), the number of steps based on the number of alternatives (2.1), and the runtime in relation to the number of alternatives (2.2).

##### Part 1:

<p align="center">
<img width="656" alt="tests1" src="https://github.com/mykhailo-arkhipov/Consistency_Increaser/assets/122175623/ac2cdc61-0065-42c7-aae4-59b675096960">
</p>

##### Part 2-1:

<p align="center">
<img width="656" alt="tests2" src="https://github.com/mykhailo-arkhipov/Consistency_Increaser/assets/122175623/5877f4f9-6642-4407-99ef-23d7551cf66b">
</p>

##### Part 2-2:

<p align="center">
<img width="656" alt="tests 3" src="https://github.com/mykhailo-arkhipov/Consistency_Increaser/assets/122175623/5fe3f365-d74d-4515-9908-495b78db5e13">
</p>

#### Commercial potential of the work

As an implemented component, means of enhancing consistency for different types of matrices are integrated into some products that work with decision-making systems. These systems are in demand in the field of decision management or when dealing with the ranking of large volumes of data performed in binary matrices of pairwise comparisons.
