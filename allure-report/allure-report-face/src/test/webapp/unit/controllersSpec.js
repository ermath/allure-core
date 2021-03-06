/*global describe:true, it:true, beforeEach:true, afterEach:true, expect:true, spyOn:true, module:true, inject:true, angular:true, jasmine:true */
describe('controllers', function () {
    'use strict';
    var $controller, $rootScope;

    beforeEach(module('allure.controllers'));
    beforeEach(inject(function (_$controller_, _$rootScope_) {
        $controller = _$controller_;
        $rootScope = _$rootScope_;
    }));

    describe('GraphCtrl', function() {
        function createController(testcases) {
            var scope = $rootScope.$new();
            scope = scope.$new();
            $controller('GraphCtrl', {
                $scope: scope,
                testcases: {testCases: testcases},
                status: {
                    all: ['FAILED', 'BROKEN', 'PASSED', 'SKIPPED']
                }
            });
            return scope;
        }

        it('should detect that all tests passed', function() {
            var scope = createController([{status: 'PASSED'}, {status: 'PASSED'}, {status: 'PASSED'}]);
            expect(scope.testsPassed).toBe(true);
        });

        it('should detect that some tests failed', function() {
            var scope = createController([{status: 'FAILED'}, {status: 'PASSED'}, {status: 'PASSED'}]);
            expect(scope.testsPassed).toBe(false);
        });

        it('should format pie-chart data', function() {
            var scope = createController([{status: 'FAILED'}, {status: 'PASSED'}, {status: 'PASSED'}]);
            expect(scope.statistic).toEqual({
                passed: 2, skipped: 0, failed: 1, broken: 0,
                total: 3
            });
            expect(scope.chartData).toEqual([
                {name: 'failed', value: 1, part: 1/3},
                {name: 'broken', value: 0, part: 0},
                {name: 'passed', value: 2, part: 2/3},
                {name: 'skipped', value: 0, part: 0}
            ]);
        });
    });
});
