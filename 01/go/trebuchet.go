package main

import (
	"bufio"
	"fmt"
	"os"
	"sort"
	"strconv"
	"strings"
	"unicode"
)

func main() {
	part1()
	part2()
}

func part1() {
	var total int = 0
	var line_number strings.Builder

	file, error := os.Open("example_part1.txt")
	//	file, error := os.Open("input_part1.txt")
	if error != nil {
		fmt.Println("Error opening file:", error)
		return
	}
	defer file.Close()

	scanner := bufio.NewScanner(file)

	for scanner.Scan() {
		line := scanner.Text()
		for i := 0; i < len(line); i++ {
			if unicode.IsDigit(rune(line[i])) {
				line_number.WriteRune(rune(line[i]))
				break
			}
		}
		for i := len(line) - 1; i > 0; i-- {
			if unicode.IsDigit(rune(line[i])) {
				line_number.WriteRune(rune(line[i]))
				break
			}
		}
		number, _ := strconv.Atoi(line_number.String())
		line_number.Reset()
		total = total + number
	}
	fmt.Printf("total: %v\n", total)
}

func part2() {
	dictionnary := map[string]string{
		"1": "one",
		"2": "two",
		"3": "three",
		"4": "four",
		"5": "five",
		"6": "six",
		"7": "seven",
		"8": "eight",
		"9": "nine",
	}
	total := 0
	file, error := os.Open("example_part2.txt")
	//file, error := os.Open("input_part2.txt")
	if error != nil {
		fmt.Println("Error opening file:", error)
		return
	}
	defer file.Close()

	scanner := bufio.NewScanner(file)

	for scanner.Scan() {
		line := scanner.Text()
		index := make(map[int]string)
		for key := range dictionnary {
			index[strings.Index(line, dictionnary[key])] = dictionnary[key]
			index[strings.Index(line, key)] = key
		}
		delete(index, -1)
		index_keys := make([]int, 0, len(index))
		for key := range index {
			index_keys = append(index_keys, key)
		}
		sort.Ints(index_keys)
		smallest_index_key := index_keys[0]
		largest_index_key := index_keys[len(index_keys)-1]
		line_result := index[smallest_index_key] + index[largest_index_key]
		line_result_int := line_result
		for key, value := range dictionnary {
			line_result_int = strings.ReplaceAll(line_result_int, value, key)
		}
		line_int, _ := strconv.Atoi(line_result_int)
		total += line_int
	}
	if error := scanner.Err(); error != nil {
		fmt.Println("Error reading file:", error)
	}
	fmt.Printf("total: %v\n", total)

}
