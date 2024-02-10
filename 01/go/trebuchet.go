package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
	"text/scanner"
	"unicode"
)

func main() {
	//fmt.Println("Hello World!")
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
		for i:=0; i < len(line); i++ {
			if unicode.IsDigit(rune(line[i])) {
			line_number.WriteRune(rune(line[i]))
			break
			}
		}
		for i:=len(line)-1; i > 0; i-- {
			if unicode.IsDigit(rune(line[i])) {
			line_number.WriteRune(rune(line[i]))
			break
			}
		}
		number, _ := strconv.Atoi(line_number.String())
//		fmt.Println("string number: " + line_number.String())
		line_number.Reset()
		total = total + number
//		fmt.Printf("total: %v\n", total)
	}
//	fmt.Println("string number: " + line_number.String())
	fmt.Printf("total: %v\n", total)
}

func part2() {
	file, error := os.Open("example_part2.txt")
//	file, error := os.Open("input_part2.txt")
	if error != nil {
		fmt.Println("Error opening file:", error)
		return
	}
	defer file.Close()

	scanner := bufio.NewScanner(file)

	for scanner.Scan() {
		line := scanner.Text()
	}
	if error := scanner.Err(); error != nil {
		fmt.Println("Error reading file:", error)
	}
}
